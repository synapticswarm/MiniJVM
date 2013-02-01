package com.synapticswarm.minijvm.ui.model;

import com.synapticswarm.minijvm.model.*;
import com.synapticswarm.minijvm.opcode.*;
import javafx.collections.ObservableList;

public class ClassFileFactory {
    private ObservableList<ConstantPoolEntryDisplayModel> constantPoolEntries;
    private ObservableList<MethodEntryDisplayModel> methodEntries;

    public static MiniClassFile parseClassFile(
            ObservableList<ConstantPoolEntryDisplayModel> constantPoolModel,
            ObservableList<MethodEntryDisplayModel> methodEntriesModel) throws Exception {

        MiniClassFile myClassFile = new MiniClassFile();

        MiniConstantPool constantPool = new MiniConstantPool(
                constantPoolModel.size());

        for (ConstantPoolEntryDisplayModel model : constantPoolModel) {
            int index = Integer.parseInt(model.getIndexProperty());
            String type = model.getTypeProperty();
            String value = model.getValueProperty();
            AbstractConstantPoolType cpType = null;

            try {
                //NB: could switch on the String in Java7
                if ("Integer".equals(type)) {
                    cpType = new CPInteger(value);
                } else if ("Utf8".equals(type)) {
                    cpType = new CPUtf8(value);
                } else if ("Class".equals(type)) {
                    cpType = new CPClass(value);
                } else if ("Fieldref".equals(type)) {
                    cpType = new CPFieldref(value);
                } else if ("NameAndType".equals(type)) {
                    cpType = new CPNameAndType(value);
                } else if ("Methodref".equals(type)) {
                    cpType = new CPMethodref(value);
                } else if ("String".equals(type)) {
                    cpType = new CPString(value);
                } else {
                    throw new Exception("Type is unrecognized!" + type);
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

            String opCodeStr = model.getOpcodeProperty();
            String argStr = model.getArgProperty();
            String offSetStr = model.getOffsetProperty();
            String commentStr = model.getCommentProperty();

            int offSet = -1;

            try {
                offSet = Integer.parseInt(offSetStr);
            } catch (Exception ex) {
                throw new Exception("Offset " + argStr + " is not a number");
            }

            IOpCode opCode = null;

            if ("getstatic".equals(opCodeStr)) {
                opCode = new GetStatic();
            }
            if ("invokevirtual".equals(opCodeStr)) {
                opCode = new InvokeVirtual();
            }
            if ("ldc".equals(opCodeStr)) {
                opCode = new Ldc();
            }
            if ("imul".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.imul();
            }
            if ("iadd".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iadd();
            }
            if ("iload_0".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iload_0();
            }
            if ("iload_1".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iload_1();
            }
            if ("iload_2".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iload_2();
            }
            if ("iload_3".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iload_3();
            }
            if ("istore_0".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.istore_0();
            }
            if ("istore_1".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.istore_1();
            }
            if ("istore_2".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.istore_2();
            }
            if ("istore_3".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.istore_3();
            }
            if ("iconst_0".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iconst_0();
            }
            if ("iconst_1".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iconst_1();
            }
            if ("iconst_2".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iconst_2();
            }
            if ("iconst_3".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iconst_3();
            }
            if ("return".equals(opCodeStr)) {
                opCode = new SimpleOpCodes._return();
            } else {
                throw new Exception("unrecognized opcode " + opCodeStr);
            }

            opCode.checkAndSetArguments(offSet, argStr, commentStr);

            mainMethod.getEntries().add(entry);
        }

        return myClassFile;
    }

    private static void checkNoArg(String argStr, String opCode) {
        if (argStr != null || argStr.length() > 0) {
            throw new RuntimeException("Opcode " + opCode + " should not have an argument:" + argStr);
        }
    }

    private static void checkArg(int i) {
        if (i == -1) throw new RuntimeException("Needs an arg!");
    }
}
