package com.synapticswarm.minijvm.model;

import com.synapticswarm.minijvm.opcode.Helper;

public abstract class BaseConstantPoolEntry implements ConstantPoolEntry{
    private String rawStringValue;
    private String comment;

    @Override
    public void checkAndSetArguments(String value, String comment) throws Exception {
        Helper.checkValueIsNullOrEmpty(value, getDisplayName());
        setRawStringValue(value);
        //don't bother checking commment
        setComment(comment);
    }

    @Override
    public String getRawStringValue() {
        return rawStringValue;
    }

    @Override
    public void setRawStringValue(String s){
        rawStringValue = s;
    }

    @Override
    public String getComment() {
        return comment;
    }

//    public void setValue(String value) {
//        this.value = value;
//    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String lookupValue(MiniConstantPool mcp) {
        throw new RuntimeException("Not implemented");
    }
}
