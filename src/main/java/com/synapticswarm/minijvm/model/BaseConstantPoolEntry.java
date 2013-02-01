package com.synapticswarm.minijvm.model;

public abstract class BaseConstantPoolEntry implements ConstantPoolEntry{
    private String value;
    private String comment;

    @Override
    public void checkAndSetArguments(String value, String comment) throws Exception {
        checkValueIsNullOrEmpty();
        setValue(value);
        //don't bother checking commment
        setComment(comment);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getComment() {
        return comment;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void checkValueIsNullOrEmpty() throws Exception{
        String arg = getValue();
        if (arg != null && arg.length() > 0){
            throw new Exception("Argument " + arg + " should be null for OpCode " + getDisplayName());
        }
    }

    public boolean checkArgHasTwoIndexes(String arg) throws Exception{
        if (arg == null || (!arg.matches("#[0-9]{1,2}\\.#[0-9]{1,2}"))){
            throw new Exception("Argument " + arg + " must be of the format #[0-9]{1,2}.#[0-9]{1,2} for OpCode " + getDisplayName());
        }
        return true;
    }

    public int [] split(String arg){
        //TODO test
        int left = Integer.parseInt(arg.substring(1,arg.indexOf(".")));
        int right = Integer.parseInt(arg.substring(arg.indexOf(".") + arg.length()));
        return new int [] {left, right};
    }
}
