package com.synapticswarm.minijvm.ui.model;

import javafx.collections.ObservableList;

import com.synapticswarm.minijvm.model.AbstractConstantPoolType;
import com.synapticswarm.minijvm.model.CPClass;
import com.synapticswarm.minijvm.model.CPFieldref;
import com.synapticswarm.minijvm.model.CPInteger;
import com.synapticswarm.minijvm.model.CPMethodref;
import com.synapticswarm.minijvm.model.CPNameAndType;
import com.synapticswarm.minijvm.model.CPString;
import com.synapticswarm.minijvm.model.CPUtf8;
import com.synapticswarm.minijvm.model.MiniClassFile;
import com.synapticswarm.minijvm.model.MiniConstantPool;
import com.synapticswarm.minijvm.model.MiniMethod;
import com.synapticswarm.minijvm.model.MiniMethodEntry;
import com.synapticswarm.minijvm.opcode.GetStatic;
import com.synapticswarm.minijvm.opcode.InvokeVirtual;
import com.synapticswarm.minijvm.opcode.Ldc;
import com.synapticswarm.minijvm.opcode.OpCode;
import com.synapticswarm.minijvm.opcode.SimpleOpCodes;

public class ClassFileFactory {
	private ObservableList<ConstantPoolEntryDisplayModel> constantPoolEntries;
	private ObservableList<MethodEntryDisplayModel> methodEntries;

	public static MiniClassFile parseClassFile(
			ObservableList<ConstantPoolEntryDisplayModel> constantPoolModel,
			ObservableList<MethodEntryDisplayModel> methodEntriesModel) {

		MiniClassFile myClassFile = new MiniClassFile();

		MiniConstantPool constantPool = new MiniConstantPool(
				constantPoolModel.size());

		for (ConstantPoolEntryDisplayModel model : constantPoolModel) {
			int index = Integer.parseInt(model.getIndexProperty());
			String type = model.getTypeProperty();
			String value = model.getValueProperty();
			AbstractConstantPoolType cpType = null;

			try {
				switch (type) {
				case "Integer":
					cpType = new CPInteger(value);
					break;
				case "Utf8":
					cpType = new CPUtf8(value);
					break;
				case "Class":
					cpType = new CPClass(value);
					break;
				case "Fieldref":
					cpType = new CPFieldref(value);
					break;
				case "NameAndType":
					cpType = new CPNameAndType(value);
					break;
				case "Methodref":
					cpType = new CPMethodref(value);
					break;
				case "String":
					cpType = new CPString(value);
					break;
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			constantPool.set(cpType, index);
		}
		myClassFile.setConstantPool(constantPool);

		// TODO simplified here for just 1 method with no params etc.
		MiniMethod mainMethod = new MiniMethod();
		myClassFile.setMainMethod(mainMethod);

		for (MethodEntryDisplayModel model : methodEntriesModel) {
			MiniMethodEntry entry = new MiniMethodEntry();
			entry.setOffset(Integer.parseInt(model.getOffsetProperty()));

			String argStr = model.getArgProperty();
			int arg = -1;
			
			if (argStr.startsWith("#")) {
				// strip off leading hash
				entry.setArg(Integer.parseInt(argStr.substring(1, 2)));
			}
			OpCode opCode = null;

			switch (model.getOpcodeProperty()) {
			case "getstatic":
				checkArg(arg);
				opCode = new GetStatic(arg);
				break;
			case "invokevirtual":
				checkArg(arg);
				opCode = new InvokeVirtual(arg);
				break;
			case "ldc":
				checkArg(arg);
				opCode = new Ldc(arg);
				break;
			case "imul":
				opCode = new SimpleOpCodes.imul();
				break;
			case "iadd":
				opCode = new SimpleOpCodes.iadd();
				break;
			case "iload_1":
				opCode = new SimpleOpCodes.iload_1();
				break;
			case "iload_2":
				opCode = new SimpleOpCodes.iload_2();
				break;
			case "iload_3":
				opCode = new SimpleOpCodes.iload_3();
				break;
			case "return":
				opCode = new SimpleOpCodes._return();
				break;
			}
			entry.setOpCode(opCode);
		}

		return myClassFile;
	}
	
	private static void checkArg(int i){
		if (i == -1) throw new RuntimeException("Needs an arg!");
	}
}
