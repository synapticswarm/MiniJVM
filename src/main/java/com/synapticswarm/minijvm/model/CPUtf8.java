package com.synapticswarm.minijvm.model;

public class CPUtf8 extends BaseConstantPoolEntry{
    private String stringValue;

    @Override
    public void checkAndSetArguments(String value, String comment) throws Exception {
        setStringValue(value);
        setComment(comment);
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String getDisplayName(){
		return "Utf8";
	}

	@Override
	public String toString() {
		return getDisplayName() + ":" + getRawStringValue();
	}

}