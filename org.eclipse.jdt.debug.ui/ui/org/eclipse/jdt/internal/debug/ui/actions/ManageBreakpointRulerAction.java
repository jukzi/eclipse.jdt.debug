/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.internal.debug.ui.actions;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.jdt.internal.debug.ui.JDIDebugUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.ui.texteditor.AbstractMarkerAnnotationModel;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

public class ManageBreakpointRulerAction extends Action {	
	
	private IVerticalRulerInfo fRuler;
	private ITextEditor fTextEditor;
	private ToggleBreakpointAdapter fBreakpointAdapter;

	public ManageBreakpointRulerAction(IVerticalRulerInfo ruler, ITextEditor editor) {
		super(ActionMessages.getString("ManageBreakpointRulerAction.label")); //$NON-NLS-1$
		fRuler= ruler;
		fTextEditor= editor;
		fBreakpointAdapter = new ToggleBreakpointAdapter();
	}
	
	/**
	 * Disposes this action
	 */
	public void dispose() {
		fTextEditor = null;
		fRuler = null;
	}
		
	/**
	 * Returns this action's vertical ruler info.
	 *
	 * @return this action's vertical ruler
	 */
	protected IVerticalRulerInfo getVerticalRulerInfo() {
		return fRuler;
	}
	
	/**
	 * Returns this action's editor.
	 *
	 * @return this action's editor
	 */
	protected ITextEditor getTextEditor() {
		return fTextEditor;
	}
	
	/**
	 * Returns the <code>IDocument</code> of the editor's input.
	 *
	 * @return the document of the editor's input
	 */
	protected IDocument getDocument() {
		IDocumentProvider provider= fTextEditor.getDocumentProvider();
		return provider.getDocument(fTextEditor.getEditorInput());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		try {
			List list = getMarkers();
			if (list.isEmpty()) {
				// create new markers
				IDocument document= getDocument();
				int lineNumber= getVerticalRulerInfo().getLineOfLastMouseButtonActivity();
				if (lineNumber >= document.getNumberOfLines()) {
					return;
				}
				try {
					IRegion line= document.getLineInformation(lineNumber);
					ITextSelection selection = new TextSelection(document, line.getOffset(), line.getLength());
					fBreakpointAdapter.toggleLineBreakpoints(fTextEditor, selection, true);
				} catch (BadLocationException e) {
					//likely document is folded so you cannot get the line information of the folded line
				}
			} else {
				// remove existing breakpoints of any type
				IBreakpointManager manager = DebugPlugin.getDefault().getBreakpointManager();
				Iterator iterator = list.iterator();
				while (iterator.hasNext()) {
					IMarker marker = (IMarker) iterator.next();
					IBreakpoint breakpoint = manager.getBreakpoint(marker);
					if (breakpoint != null) {
						breakpoint.delete();
					}
				}
			}
		} catch (CoreException e) {
			JDIDebugUIPlugin.errorDialog(ActionMessages.getString("ManageBreakpointRulerAction.error.adding.message1"), e); //$NON-NLS-1$
		}
	}		
	
	/**
	 * Returns a list of markers that exist at the current ruler location.
	 * 
	 * @return a list of markers that exist at the current ruler location
	 */
	protected List getMarkers() {
		
		List breakpoints= new ArrayList();
		
		IResource resource= ToggleBreakpointAdapter.getResource(fTextEditor);
		IDocument document= getDocument();
		AbstractMarkerAnnotationModel model= getAnnotationModel();
		
		if (model != null) {
			try {
				
				IMarker[] markers= null;
				if (resource instanceof IFile)
					markers= resource.findMarkers(IBreakpoint.BREAKPOINT_MARKER, true, IResource.DEPTH_INFINITE);
				else {
					IWorkspaceRoot root= ResourcesPlugin.getWorkspace().getRoot();
					markers= root.findMarkers(IBreakpoint.BREAKPOINT_MARKER, true, IResource.DEPTH_INFINITE);
				}
				
				if (markers != null) {
					IBreakpointManager breakpointManager= DebugPlugin.getDefault().getBreakpointManager();
					for (int i= 0; i < markers.length; i++) {
						IBreakpoint breakpoint= breakpointManager.getBreakpoint(markers[i]);
						if (breakpoint != null && breakpointManager.isRegistered(breakpoint) && 
								includesRulerLine(model.getMarkerPosition(markers[i]), document))
							breakpoints.add(markers[i]);
					}
				}
			} catch (CoreException x) {
				JDIDebugUIPlugin.log(x.getStatus());
			}
		}
		return breakpoints;
	}	
	
	/**
	 * Returns the <code>AbstractMarkerAnnotationModel</code> of the editor's input.
	 *
	 * @return the marker annotation model
	 */
	protected AbstractMarkerAnnotationModel getAnnotationModel() {
		IDocumentProvider provider= fTextEditor.getDocumentProvider();
		IAnnotationModel model= provider.getAnnotationModel(fTextEditor.getEditorInput());
		if (model instanceof AbstractMarkerAnnotationModel) {
			return (AbstractMarkerAnnotationModel) model;
		}
		return null;
	}	
	
	/**
	 * Checks whether a position includes the ruler's line of activity.
	 *
	 * @param position the position to be checked
	 * @param document the document the position refers to
	 * @return <code>true</code> if the line is included by the given position
	 */
	protected boolean includesRulerLine(Position position, IDocument document) {

		if (position != null) {
			try {
				int markerLine= document.getLineOfOffset(position.getOffset());
				int line= fRuler.getLineOfLastMouseButtonActivity();
				if (line == markerLine) {
					return true;
				}
			} catch (BadLocationException x) {
			}
		}
		
		return false;
	}	
}
