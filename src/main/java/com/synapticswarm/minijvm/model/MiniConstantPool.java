package com.synapticswarm.minijvm.model;


public class MiniConstantPool{

	private final ConstantPoolEntry [] entries;
	
	public MiniConstantPool(int size){
		//array is zero based but constant pool is 1-based so we will make it one slot bigger and ignore slot 0.
		this.entries = new ConstantPoolEntry [size + 1];
	}
	
	public ConstantPoolEntry[] getEntries() {
		return entries;
	}
	
	public ConstantPoolEntry get(int i){
		if (i == 0) throw new RuntimeException("Array is 1-based!");
		return this.entries[i];
	}
	
	public void set(ConstantPoolEntry a, int i){
		if (i == 0) throw new RuntimeException("Array is 1-based!");
		this.entries[i] = a;
	}

	@Override
	public String toString() {
		StringBuilder sb  = new StringBuilder();
		sb.append("ConstantPool>>");
		
		for (int i = 1; i < this.entries.length; i++){
			sb.append(this.entries[i].toString());
		}
		
		sb.append("<<ConstantPool");
		return super.toString();
	}
	
	
	
}
