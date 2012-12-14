package com.synapticswarm.minijvm.opcode;

import java.lang.reflect.Method;

import com.synapticswarm.minijvm.JVM.MethodContext;
import com.synapticswarm.minijvm.MiniStack;
import com.synapticswarm.minijvm.model.CPClass;
import com.synapticswarm.minijvm.model.CPMethodref;
import com.synapticswarm.minijvm.model.CPNameAndType;
import com.synapticswarm.minijvm.model.CPUtf8;
import com.synapticswarm.minijvm.model.MiniConstantPool;

/**
 * At present this is pretty limited and just invokes static void methods (but they can take arguments, except varargs ones)
 * on public classes.
 * 
 * @author john
 *
 */
public class InvokeVirtual implements OpCode {
	private int arg;	
	
	public InvokeVirtual(int arg){
		this.arg = arg;
	}

	/**
	 * TODO this currently only works for methods with 1 parameter. We cut corners a bit here
	 * and don't use everything from the constant pool. Also, we cheat: we invoke a method in the JVM
	 * which our miniJVM is running in, rather than within our miniJVM.
	 * 
	 * @param jvm
	 * @param methodContext
	 * @param currentEntry
	 * @param constantPool
	 */
	@Override
	public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
		//NB: so far only works with methods with 1 parameter!
		// Stack should be: param first, then target object
		Object poppedParameter = stack.pop();
		Object targetObject = stack.pop();

		// the arg for this bytecode points to a Methodref in the constant pool
		CPMethodref methodref = (CPMethodref) constantPool.get(this.arg);

		// get the NameAndType entry
		CPNameAndType cpNameAndType = (CPNameAndType) constantPool
				.get(methodref.nameAndTypeIndex);

		// get the method name
		CPUtf8 cpMethodName = (CPUtf8) constantPool
				.get(cpNameAndType.methodNameIndex);
		String methodName = cpMethodName.value;
		
		Method tgt = null;
		try {
			tgt = targetObject.getClass().getMethod(methodName, poppedParameter.getClass());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		try{
			tgt.invoke(targetObject, poppedParameter);
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

}
