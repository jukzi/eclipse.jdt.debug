/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.internal.debug.ui.actions;

import org.eclipse.jdt.internal.debug.ui.IJDIPreferencesConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.custom.BusyIndicator;

/**
 * Toggle to display the thread and monitor information in the debug view.
 */
public class ShowMonitorThreadInformation extends ViewFilterAction {

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.debug.ui.actions.ViewFilterAction#getPreferenceKey()
	 */
	protected String getPreferenceKey() {
		return IJDIPreferencesConstants.PREF_SHOW_MONITOR_THREAD_INFO;
	}

	/* (non-Javadoc)
	 * This method is not actually called - this action is not a filter. Instead
	 * it sets an preference.
	 * 
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.debug.ui.actions.ViewFilterAction#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		final StructuredViewer viewer = getStructuredViewer();
		BusyIndicator.showWhile(viewer.getControl().getDisplay(), new Runnable() {
			public void run() {
				IPreferenceStore store = getPreferenceStore();
				store.setValue(getPreferenceKey(), getValue());			
				viewer.refresh();
			}
		});
	}

}
