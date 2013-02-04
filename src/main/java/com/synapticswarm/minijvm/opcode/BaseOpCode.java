package com.synapticswarm.minijvm.opcode;

public abstract class BaseOpCode implements OpCode {

    private int expectedOffSetSize = 1;//default offset
    private int offSetPosition = -1;
    private String rawArgString = null;
    private int argInt = -1;
    private String comment = null;

    public String getDisplayName(){
        return this.getClass().getSimpleName().toLowerCase();
    }

    public int getExpectedOffSetSize(){
        return 1;
    }

    public void setExpectedOffSetSize(int i){
        expectedOffSetSize = i;
    }

    public int getArgInt() {
        return argInt;
    }

    public void setArgInt(int argInt) {
        this.argInt = argInt;
    }

    public int getOffSetPosition() {
        return offSetPosition;
    }

    public void setOffSetPosition(int offSetPosition) {
        this.offSetPosition = offSetPosition;
    }

    public String getRawArgString() {
        return rawArgString;
    }

    public void setRawArgString(String rawArgString) {
        this.rawArgString = rawArgString;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    // Default behaviour is to ensure the argument is null or empty and the offSet is 1.
    public void checkAndSetArguments(int givenOffSetSize, int offSetPosition, String arg, String comment) throws Exception {
        Helper.checkValueIsNullOrEmpty(arg, getDisplayName());
        setRawArgString(arg);
        checkOffSetAsExpected(givenOffSetSize, getExpectedOffSetSize());
        setOffSetPosition(offSetPosition);
    }

    public void checkOffSetAsExpected(int supplied, int expected) throws Exception{
        if (supplied != expected){
            throw new Exception("Offset " + supplied + " should be 1 for OpCode " + getDisplayName());
        }
    }
}
