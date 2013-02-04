package com.synapticswarm.minijvm.model;

import com.synapticswarm.minijvm.opcode.Helper;

public class CPMethodref extends BaseConstantPoolEntry{
	public int classIndex = -1;
	public int nameAndTypeIndex = -1;
    private final static String DELIMITER = ".";

    @Override
    public void checkAndSetArguments(String value, String comment) throws Exception {
        this.setComment(comment);
        Helper.checkArgHasTwoIndexes(value, getDisplayName(), DELIMITER);
        int [] vals = Helper.split(value, DELIMITER);
        this.classIndex = vals[0];
        this.nameAndTypeIndex = vals[1];
    }

    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setNameAndTypeIndex(int nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public String getDisplayName(){
		return "Method";
	}

	@Override
	public String toString() {
		return getDisplayName() + ":" + classIndex + "," + nameAndTypeIndex;
	}
}