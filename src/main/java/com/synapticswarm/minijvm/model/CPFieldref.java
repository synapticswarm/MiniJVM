package com.synapticswarm.minijvm.model;

import com.synapticswarm.minijvm.opcode.Helper;

public class CPFieldref extends BaseConstantPoolEntry{
	public int classIndex = -1;
	public int nameAndTypeIndex = -1;

    @Override
    public void checkAndSetArguments(String value, String comment) throws Exception {
        checkArgHasTwoIndexes(value);
        int [] vals = split(value);
        this.classIndex = vals[0];
        this.nameAndTypeIndex = vals [1];

        setComment(comment);
    }

	public String getDisplayName(){
		return "NameAndType";
	}

	@Override
	public String toString() {
		return getDisplayName() + ":" + classIndex + "," + nameAndTypeIndex;
	}
	
}