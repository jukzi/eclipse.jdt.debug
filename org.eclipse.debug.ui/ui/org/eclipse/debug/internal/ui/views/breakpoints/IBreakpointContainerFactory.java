/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.debug.internal.ui.views.breakpoints;

import org.eclipse.debug.ui.IBreakpointContainerFactoryDelegate;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Factory which produces <code>IBreakpointContainer</code>s based on some
 * factory-specific criteria.
 * 
 * Clients are not intended to implement this interface. Instead, they should
 * extend AbstractBreakpointContainerFactory.
 * 
 * @since 3.1
 */
public interface IBreakpointContainerFactory {
	
	/**
	 * Returns containers for the given set of breakpoints. Each of the
	 * given breakpoints will appear in exactly one of the returned containers.
	 * 
	 * @param container the parent container for the containers that will be
	 *  created or <code>null</code> if none.
	 * @return containers for the given set of breakpoints
	 */
	public IBreakpointContainer[] getContainers(IBreakpointContainer parentContainer);
	
	/**
	 * Returns a user-presentable label for this breakpoint container factory
	 * @return this breakpoint container factory's label
	 */
	public String getLabel();
	
	/**
	 * Returns an image for presentation along with this factory or <code>null</code>
	 * if none.
	 * @return an image for presentation along with this factory or <code>null</code>
	 */
	public ImageDescriptor getImageDescriptor();
	
	/**
	 * Returns this breakpoint container factory's unique identifier.
	 * @return this breakpoint container factory's unique identifier
	 */
	public String getIdentifier();
	
	/**
	 * Returns the delegate that this factory uses to create breakpoint containers
	 * or <code>null</code> if none was correctly specified.
	 * @return this factory's delegate or <code>null</code>
	 */
	public IBreakpointContainerFactoryDelegate getDelegate();
	
	/**
	 * Disposes this container factory and its delegate. Allows clients to clean
	 * up any resources (such as images) that it may have in use.
	 */
	public void dispose();

}
