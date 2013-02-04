package com.synapticswarm.minijvm.model;

import com.synapticswarm.minijvm.opcode.Helper;

public class CPInteger extends BaseConstantPoolEntry {
	private int intValue;

    @Override
    public void checkAndSetArguments(String value, String comment) throws Exception {
        setComment(comment);
        Helper.checkArgHasOneIndex(value, getDisplayName());
        setIntValue(Integer.parseInt(Helper.stripLeadingHash(value)));
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    @Override
    public String getDisplayName() {
		return "Integer";
	}

	@Override
	public String toString() {
		return getDisplayName() + ":" + getIntValue();
	}
}