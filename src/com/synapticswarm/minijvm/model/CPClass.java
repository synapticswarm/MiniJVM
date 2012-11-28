package com.synapticswarm.minijvm.model;

import com.synapticswarm.minijvm.opcode.Helper;

public class CPClass extends AbstractConstantPoolType {
	public int classNameIndex = -1;

	public CPClass(String val) {
		int intVal = Integer.parseInt(Helper.stripLeadingHash(val));
		this.classNameIndex = intVal;
	}

	public String getName() {
		return "Class";
	}

	@Override
	public String toString() {
		return getName() + ":" + classNameIndex;
	}
}