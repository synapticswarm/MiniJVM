package com.synapticswarm.minijvm.model;

public interface ConstantPoolEntry {
    void checkAndSetArguments(String arg, String comment) throws Exception;
    String getValue();
    String getComment();
    String getDisplayName();
}
