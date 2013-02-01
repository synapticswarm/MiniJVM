package com.synapticswarm.minijvm.opcode;

import com.synapticswarm.minijvm.MiniStack;
import com.synapticswarm.minijvm.JVM.MethodContext;
import com.synapticswarm.minijvm.model.MiniConstantPool;

public abstract class BaseOpCode implements IOpCode{

    private int offSet = 1;//default offset
    private String arg = null;
    private String comment = null;

    public String getDisplayName(){
        return this.getClass().getSimpleName().toLowerCase();
    }

    public int getOffSet(){
        return 1;
    }

    public void setOffSet(int i){
        offSet = i;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    // Default behaviour is to ensure the argument is null or empty and the offSet is 1.
    public void checkAndSetArguments(int offSet, String arg, String comment) throws Exception {
        checkArgumentIsNullOrEmpty();
        setArg(arg);
        checkOffSet(offSet);
        setOffSet(offSet);
    }

    public void checkArgumentIsNullOrEmpty() throws Exception{
        String arg = getArg();
        if (arg != null && arg.length() > 0){
            throw new Exception("Argument " + arg + " should be null for OpCode " + getDisplayName());
        }
    }

    //checks is of format #1 or #12
    protected void checkArgumentHasValue(String arg) throws Exception{
        if (arg == null || (!arg.matches("#[0-9]{1,2}"))){
            throw new Exception("Argument " + arg + " must be of the format #[0-9]{1,2} for OpCode " + getDisplayName());
        }
    }

//    protected int getArgValue(String arg) throws Exception{
//        if (arg == null || (!arg.matches("#[0-9]{1,2}"))){
//            throw new Exception("Argument " + arg + " must be of the format #<number> for OpCode " + getDisplayName());
//        }
//
//        return Integer.parseInt(arg);??
//    }

    public void checkOffSet(int a_OffSet) throws Exception{
        if (getOffSet() != a_OffSet){
            throw new Exception("Offset " + offSet + " should be 1 for OpCode " + getDisplayName());
        }
    }
}
