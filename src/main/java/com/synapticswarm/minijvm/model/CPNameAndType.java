package com.synapticswarm.minijvm.model;

import com.synapticswarm.minijvm.opcode.Helper;

public class CPNameAndType extends BaseConstantPoolEntry{
	public int methodNameIndex = -1;
	public int methodTypeDescriptorIndex = -1;
    private final static String DELIMITER = ":";

    @Override
    public void checkAndSetArguments(String value, String comment) throws Exception {
        setComment(comment);
        Helper.checkArgHasTwoIndexes(value, getDisplayName(), DELIMITER);
        int [] vals = Helper.split(value, DELIMITER);
        this.methodNameIndex = vals[0];
        this.methodTypeDescriptorIndex = vals[1];
    }

    public int getMethodNameIndex() {
        return methodNameIndex;
    }

    public void setMethodNameIndex(int methodNameIndex) {
        this.methodNameIndex = methodNameIndex;
    }

    public int getMethodTypeDescriptorIndex() {
        return methodTypeDescriptorIndex;
    }

    public void setMethodTypeDescriptorIndex(int methodTypeDescriptorIndex) {
        this.methodTypeDescriptorIndex = methodTypeDescriptorIndex;
    }

    @Override
    public String getDisplayName(){
		return "NameAndType";
	}
	
	@Override
	public String toString() {
		return getDisplayName() + ":" + methodNameIndex + "," + methodTypeDescriptorIndex;
	}
}