package com.synapticswarm.minijvm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Semaphore;

import com.synapticswarm.minijvm.model.MiniClassFile;
import com.synapticswarm.minijvm.model.MiniMethodEntry;

public class JVM {
	
	/**
	 * Represents the context of a currently executing method, which is 
	 * primarily a set of method-scoped variables.
	 * NB: at a later stage method parameters would be on this class
	 */
	public class MethodContext{
		private Map <Integer, Object> variables = new HashMap <> ();

		public Map<Integer, Object> getVariables() {
			return variables;
		}
	}
	
	private MiniStack stack;
	private MiniClassFile classFile;
	private int stepCount = 0;
	private Semaphore semaphore = new Semaphore(1);
	
	public JVM (MiniStack stack, MiniClassFile classFile){
		this.stack = stack;
		this.classFile = classFile;
	}
	
	public void execute() throws InterruptedException{
		
		Iterator <MiniMethodEntry> entriesItr = this.classFile.getMainMethod().getEntries().iterator();
		MethodContext ctx = new MethodContext();
		
		while (entriesItr.hasNext()){
			MiniMethodEntry entry = entriesItr.next();			
			entry.getOpCode().execute(stack, this.classFile.getConstantPool(), ctx);
			
			//wait until next click of the 'step' button
			this.semaphore.acquire();
		}
		
	}
	
	public void step(){
		this.semaphore.release();
	}
	
	public int getLastExecutedStepIndex(){
		return stepCount;
	}
	
	
}
