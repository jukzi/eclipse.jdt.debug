/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.debug.internal.ui.actions.breakpointGroups;

import org.eclipse.debug.internal.ui.views.breakpoints.BreakpointsView;
import org.eclipse.debug.internal.ui.views.breakpoints.IBreakpointOrganizer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

/**
 * An action which sets the breakpoint factory on a breakpoint view,
 * effectively telling the view to group breakpoints according to
 * some criteria (as determined by the factory).
 */
public class GroupBreakpointsAction extends Action {
    
    private IBreakpointOrganizer fOrganzier;
    private BreakpointsView fView;

    /**
     * Creates a new action which will group breakpoints in the given
     * breakpoint view using the given breakpoint container factory
     * @param factory the factory that will be applied to the given view
     *  when this action is run
     * @param view the breakpoints view
     */
    public GroupBreakpointsAction(IBreakpointOrganizer organizer, BreakpointsView view) {
        super("", IAction.AS_RADIO_BUTTON); //$NON-NLS-1$
        fOrganzier= organizer;
        fView= view;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        if (isChecked()) {
            if (fOrganzier == null) {
                fView.setBreakpointOrganizers(null);
            } else {
                fView.setBreakpointOrganizers(new IBreakpointOrganizer[]{fOrganzier});
            }
        }
    }
    
    /**
     * Returns this action's organizer.
     * 
     * @return breakpoint organizer
     */
    public IBreakpointOrganizer getOrganizer() {
    	return fOrganzier;
    }
}
