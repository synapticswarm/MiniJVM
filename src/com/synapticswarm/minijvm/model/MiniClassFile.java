package com.synapticswarm.minijvm.model;


public class MiniClassFile {
	private MiniConstantPool constantPool;
	private MiniMethod mainMethod;
	
	public MiniConstantPool getConstantPool() {
		return constantPool;
	}

	public void setConstantPool(MiniConstantPool constantPool) {
		this.constantPool = constantPool;
	}

	public MiniMethod getMainMethod() {
		return mainMethod;
	}

	public void setMainMethod(MiniMethod mainMethod) {
		this.mainMethod = mainMethod;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("MyClassFile>>\n");
		
		sb.append(" Constant Pool is:\n");
		
		for (int i = 1; i < constantPool.getEntries().length; i++){
			sb.append(constantPool.get(i).toString());
		}
		
		sb.append(" Method is:" + mainMethod.toString());
		
		return sb.toString();
	}
	
	
}
