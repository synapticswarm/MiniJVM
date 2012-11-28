package com.synapticswarm.minijvm.opcode;

import com.synapticswarm.minijvm.JVM;
import com.synapticswarm.minijvm.JVM.MethodContext;
import com.synapticswarm.minijvm.MiniStack;
import com.synapticswarm.minijvm.model.MiniConstantPool;
import com.synapticswarm.minijvm.model.MiniMethod;
import com.synapticswarm.minijvm.model.MiniMethodEntry;

public class SimpleOpCodes {

	public static class _return implements OpCode {
		public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
			return;
		}
	}
	
	public static class imul implements OpCode {
		public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
			Integer a = (Integer) stack.pop();
			Integer b = (Integer) stack.pop();
			stack.push(new Integer(a * b));
		}
	}

	public static class iadd implements OpCode {
		public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
			Integer a = (Integer) stack.pop();
			Integer b = (Integer) stack.pop();
			stack.push(new Integer(a + b));
		}
	}

	private static final int ARG_OFFSET = 0; // whoops i forgot if the args are
												// zero based. just put this here to make it easy to change them!

	public static class iload_1 implements OpCode {
		public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
			stack.push(ctx.getVariables().get(ARG_OFFSET + 1));
		}
	}
	
	public static class iconst_1 implements OpCode{
		public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
			stack.push(new Integer(1));			
		}
	}
	
	public static class iload_2 implements OpCode {
		public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
			stack.push(ctx.getVariables().get(ARG_OFFSET + 2));
		}
	}

	public static class iload_3 implements OpCode {
		public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
			stack.push(ctx.getVariables().get(ARG_OFFSET + 3));
		}
	}

	public static class iconst_2 implements OpCode{
		public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
			stack.push(new Integer(2));			
		}
	}


}
