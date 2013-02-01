package com.synapticswarm.minijvm.opcode;

import com.synapticswarm.minijvm.JVM.MethodContext;
import com.synapticswarm.minijvm.MiniStack;
import com.synapticswarm.minijvm.model.MiniConstantPool;
import com.synapticswarm.minijvm.opcode.BaseOpCode;

public class SimpleOpCodes {

	public static class _return extends BaseOpCode {
		public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
			return;
		}
	}
	
	public static class imul extends BaseOpCode {
		public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
			Integer a = (Integer) stack.pop();
			Integer b = (Integer) stack.pop();
			stack.push(new Integer(a * b));
		}
	}

	public static class iadd extends BaseOpCode {
		public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
			Integer a = (Integer) stack.pop();
			Integer b = (Integer) stack.pop();
			stack.push(new Integer(a + b));
		}
	}

	private static final int ARG_OFFSET = 0; // whoops i forgot if the args are
												// zero based or not! I just put this here to make it easy to change them!

    public static class iload_0 extends BaseOpCode {
        public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
            stack.push(ctx.getVariables().get(ARG_OFFSET + 0));
        }
    }

	public static class iload_1 extends BaseOpCode {
		public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
			stack.push(ctx.getVariables().get(ARG_OFFSET + 1));
		}
	}

    public static class iload_2 extends BaseOpCode {
        public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
            stack.push(ctx.getVariables().get(ARG_OFFSET + 2));
        }
    }

    public static class iload_3 extends BaseOpCode {
        public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
            stack.push(ctx.getVariables().get(ARG_OFFSET + 3));
        }
    }

    public static class iconst_0 extends BaseOpCode{
        public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
            stack.push(new Integer(0));
        }
    }

	public static class iconst_1 extends BaseOpCode{
		public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
			stack.push(new Integer(1));			
		}
	}

    public static class iconst_2 extends BaseOpCode{
        public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
            stack.push(new Integer(2));
        }
    }

    public static class iconst_3 extends BaseOpCode{
        public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
            stack.push(new Integer(3));
        }
    }

    public static class istore_0 extends BaseOpCode{
        public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
            ctx.getVariables().put(new Integer(0), stack.pop());
        }
    }

    public static class istore_1 extends BaseOpCode{
        public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
            ctx.getVariables().put(new Integer(1), stack.pop());
        }
    }

    public static class istore_2 extends BaseOpCode{
        public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
            ctx.getVariables().put(new Integer(2), stack.pop());
        }
    }

    public static class istore_3 extends BaseOpCode{
        public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
            ctx.getVariables().put(new Integer(3), stack.pop());
        }
    }
}
