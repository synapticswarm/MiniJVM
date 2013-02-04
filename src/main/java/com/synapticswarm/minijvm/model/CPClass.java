package com.synapticswarm.minijvm.model;

import com.synapticswarm.minijvm.opcode.Helper;

public class CPClass extends BaseConstantPoolEntry {
	public int classNameIndex = -1;

    public int getClassNameIndex() {
        return classNameIndex;
    }

    public void setClassNameIndex(int classNameIndex) {
        this.classNameIndex = classNameIndex;
    }

    @Override
    public void checkAndSetArguments(String value, String comment) throws Exception {
        setComment(comment);
        Helper.checkArgHasOneIndex(value, comment);
        setClassNameIndex(Integer.parseInt(Helper.stripLeadingHash(value)));
    }

    @Override
    public String getDisplayName() {
		return "class";
	}

	@Override
	public String toString() {
		return getDisplayName() + ":" + classNameIndex;
	}
}