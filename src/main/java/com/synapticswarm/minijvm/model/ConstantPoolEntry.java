package com.synapticswarm.minijvm.model;

public interface ConstantPoolEntry {
    void checkAndSetArguments(String arg, String comment) throws Exception;
    String getRawStringValue();
    void setRawStringValue(String s);
    String getComment();
    String getDisplayName();
    String lookupValue(MiniConstantPool mcp);
}
