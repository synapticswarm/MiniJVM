package com.synapticswarm.minijvm.opcode;

import com.synapticswarm.minijvm.JVM;
import com.synapticswarm.minijvm.JVM.MethodContext;
import com.synapticswarm.minijvm.MiniClassLoader;
import com.synapticswarm.minijvm.MiniStack;
import com.synapticswarm.minijvm.model.CPClass;
import com.synapticswarm.minijvm.model.CPFieldref;
import com.synapticswarm.minijvm.model.CPNameAndType;
import com.synapticswarm.minijvm.model.CPUtf8;
import com.synapticswarm.minijvm.model.MiniConstantPool;
import com.synapticswarm.minijvm.model.MiniMethod;
import com.synapticswarm.minijvm.model.MiniMethodEntry;

public class GetStatic implements OpCode {
	private int arg;
	
	public GetStatic(int arg){
		this.arg = arg;
	}

	/**
	 * Locates a static object and pushes it onto the stack.
	 * 
	 * @param jvm
	 * @param methodContext
	 * @param currentEntry
	 * @param constantPool
	 */
	public void execute(MiniStack stack, MiniConstantPool constantPool, MethodContext ctx) {
		// this bytecodes arg points to a Fieldref in the constant pool
		CPFieldref cpFieldRef = (CPFieldref) constantPool
				.get(this.arg);

		// Fieldref contains a Class index and a NameAndType index. First we
		// follow the chain to get the class name
		CPClass cpClass = (CPClass) constantPool
				.get(cpFieldRef.classIndex);
		// Class points to a Utf8
		CPUtf8 cpUtf8ClassName = (CPUtf8) constantPool
				.get(cpClass.classNameIndex);
		String className = cpUtf8ClassName.value;

		// Now we get the NameAndType and follow its two links to get the
		// field name and field type name
		CPNameAndType cpNameAndType = (CPNameAndType) constantPool
				.get(cpFieldRef.nameAndTypeIndex);
		CPUtf8 cpUtf8FieldName = (CPUtf8) constantPool
				.get(cpNameAndType.methodNameIndex);
		String fieldName = cpUtf8FieldName.value;
		CPUtf8 cpUtf8TypeName = (CPUtf8) constantPool
				.get(cpNameAndType.methodTypeDescriptorIndex);
		String typeName = cpUtf8TypeName.value;

		// Now we have all the information we need, we should locate the
		// object and push it onto the stack
		stack.push(MiniClassLoader.getStatic(className, fieldName));
	}

}
