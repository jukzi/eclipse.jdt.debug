/*******************************************************************************
 * Copyright (c) 2002, 2005 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.debug.tests.eval;

import org.eclipse.debug.core.model.IValue;
import org.eclipse.jdt.debug.core.IJavaPrimitiveValue;

public class TypeHierarchy_119_1 extends Tests {
	/**
	 * Constructor for TypeHierarchy.
	 */
	public TypeHierarchy_119_1(String name) {
		super(name);
	}

	public void init() throws Exception {
		initializeFrame("EvalTypeHierarchyTests", 122, 2, 1);
	}

	protected void end() throws Exception {
		destroyFrame();
	}

	public void testEvalNestedTypeTest_m1() throws Throwable {
		try {
		init();
		IValue value = eval("m1()");
		String typeName = value.getReferenceTypeName();
		assertEquals("m1 : wrong type : ", "int", typeName);
		int intValue = ((IJavaPrimitiveValue)value).getIntValue();
		assertEquals("m1 : wrong result : ", 111, intValue);
		} finally {
		end();
		}
	}

	public void testEvalNestedTypeTest_m2() throws Throwable {
		try {
		init();
		IValue value = eval("m2()");
		String typeName = value.getReferenceTypeName();
		assertEquals("m2 : wrong type : ", "int", typeName);
		int intValue = ((IJavaPrimitiveValue)value).getIntValue();
		assertEquals("m2 : wrong result : ", 222, intValue);
		} finally {
		end();
		}
	}

	public void testEvalNestedTypeTest_s2() throws Throwable {
		try {
		init();
		IValue value = eval("s2()");
		String typeName = value.getReferenceTypeName();
		assertEquals("s2 : wrong type : ", "int", typeName);
		int intValue = ((IJavaPrimitiveValue)value).getIntValue();
		assertEquals("s2 : wrong result : ", 999, intValue);
		} finally {
		end();
		}
	}

	public void testEvalNestedTypeTest_m3() throws Throwable {
		try {
		init();
		IValue value = eval("m3()");
		String typeName = value.getReferenceTypeName();
		assertEquals("m3 : wrong type : ", "int", typeName);
		int intValue = ((IJavaPrimitiveValue)value).getIntValue();
		assertEquals("m3 : wrong result : ", 333, intValue);
		} finally {
		end();
		}
	}

	public void testEvalNestedTypeTest_m4() throws Throwable {
		try {
		init();
		IValue value = eval("m4()");
		String typeName = value.getReferenceTypeName();
		assertEquals("m4 : wrong type : ", "int", typeName);
		int intValue = ((IJavaPrimitiveValue)value).getIntValue();
		assertEquals("m4 : wrong result : ", 444, intValue);
		} finally {
		end();
		}
	}

	public void testEvalNestedTypeTest_s4() throws Throwable {
		try {
		init();
		IValue value = eval("s4()");
		String typeName = value.getReferenceTypeName();
		assertEquals("s4 : wrong type : ", "int", typeName);
		int intValue = ((IJavaPrimitiveValue)value).getIntValue();
		assertEquals("s4 : wrong result : ", 888, intValue);
		} finally {
		end();
		}
	}

	public void testEvalNestedTypeTest_m5() throws Throwable {
		try {
		init();
		IValue value = eval("m5()");
		String typeName = value.getReferenceTypeName();
		assertEquals("m5 : wrong type : ", "int", typeName);
		int intValue = ((IJavaPrimitiveValue)value).getIntValue();
		assertEquals("m5 : wrong result : ", 555, intValue);
		} finally {
		end();
		}
	}

	public void testEvalNestedTypeTest_m6() throws Throwable {
		try {
		init();
		IValue value = eval("m6()");
		String typeName = value.getReferenceTypeName();
		assertEquals("m6 : wrong type : ", "int", typeName);
		int intValue = ((IJavaPrimitiveValue)value).getIntValue();
		assertEquals("m6 : wrong result : ", 666, intValue);
		} finally {
		end();
		}
	}

	public void testEvalNestedTypeTest_s6() throws Throwable {
		try {
		init();
		IValue value = eval("s6()");
		String typeName = value.getReferenceTypeName();
		assertEquals("s6 : wrong type : ", "int", typeName);
		int intValue = ((IJavaPrimitiveValue)value).getIntValue();
		assertEquals("s6 : wrong result : ", 777, intValue);
		} finally {
		end();
		}
	}

	public void testEvalNestedTypeTest_super_m1() throws Throwable {
		try {
		init();
		IValue value = eval("super.m1()");
		String typeName = value.getReferenceTypeName();
		assertEquals("super.m1 : wrong type : ", "int", typeName);
		int intValue = ((IJavaPrimitiveValue)value).getIntValue();
		assertEquals("super.m1 : wrong result : ", 11, intValue);
		} finally {
		end();
		}
	}

	public void testEvalNestedTypeTest_super_m2() throws Throwable {
		try {
		init();
		IValue value = eval("super.m2()");
		String typeName = value.getReferenceTypeName();
		assertEquals("super.m2 : wrong type : ", "int", typeName);
		int intValue = ((IJavaPrimitiveValue)value).getIntValue();
		assertEquals("super.m2 : wrong result : ", 22, intValue);
		} finally {
		end();
		}
	}

	public void testEvalNestedTypeTest_super_m3() throws Throwable {
		try {
		init();
		IValue value = eval("super.m3()");
		String typeName = value.getReferenceTypeName();
		assertEquals("super.m3 : wrong type : ", "int", typeName);
		int intValue = ((IJavaPrimitiveValue)value).getIntValue();
		assertEquals("super.m3 : wrong result : ", 33, intValue);
		} finally {
		end();
		}
	}

	public void testEvalNestedTypeTest_super_m4() throws Throwable {
		try {
		init();
		IValue value = eval("super.m4()");
		String typeName = value.getReferenceTypeName();
		assertEquals("super.m4 : wrong type : ", "int", typeName);
		int intValue = ((IJavaPrimitiveValue)value).getIntValue();
		assertEquals("super.m4 : wrong result : ", 44, intValue);
		} finally {
		end();
		}
	}

}
