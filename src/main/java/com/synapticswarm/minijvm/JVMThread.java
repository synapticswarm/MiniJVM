package com.synapticswarm.minijvm;

/**
 * Created with IntelliJ IDEA.
 * User: summersj
 * Date: 14/12/12
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public class JVMThread extends Thread{

    private JVM jvm;

    public JVMThread(JVM jvm){
        this.jvm = jvm;
    }

    @Override
    public void run(){
        this.jvm.execute();
        System.out.println("JVM Thread finished execution");
    }
}
