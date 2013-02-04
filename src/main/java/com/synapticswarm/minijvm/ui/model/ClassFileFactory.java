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
            String comment = model.getCommentProperty();
            ConstantPoolEntry constantPoolEntry = null;

            try {
                //NB: could switch on the String in Java7
                if ("Integer".equals(type)) {
                    constantPoolEntry = new CPInteger();
                } else if ("Utf8".equals(type)) {
                    constantPoolEntry = new CPUtf8();
                } else if ("Class".equals(type)) {
                    constantPoolEntry = new CPClass();
                } else if ("Fieldref".equals(type)) {
                    constantPoolEntry = new CPFieldref();
                } else if ("NameAndType".equals(type)) {
                    constantPoolEntry = new CPNameAndType();
                } else if ("Methodref".equals(type)) {
                    constantPoolEntry = new CPMethodref();
                } else if ("String".equals(type)) {
                    constantPoolEntry = new CPString();
                } else {
                    throw new Exception("Type is unrecognized!" + type);
                }
                constantPoolEntry.checkAndSetArguments(value, comment);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            constantPool.set(constantPoolEntry, index);
        }
        myClassFile.setConstantPool(constantPool);

        // TODO simplified here for just 1 method with no params etc.
        MiniMethod mainMethod = new MiniMethod();
        myClassFile.setMainMethod(mainMethod);
        int previousOffSetIndex = -1;
        int previousOffSetSize = -1;

        for (MethodEntryDisplayModel model : methodEntriesModel) {
            MiniMethodEntry entry = new MiniMethodEntry();

            String opCodeStr = model.getOpcodeProperty();
            String argStr = model.getArgProperty();
            //fyi: the displayed offset on the screen is the current offset index
            String offSetStr = model.getOffsetProperty();
            String commentStr = model.getCommentProperty();

            int currentOffSetDisplayIndex = -1;

            try {
                currentOffSetDisplayIndex = Integer.parseInt(offSetStr);
            } catch (Exception ex) {
                throw new Exception("Offset " + argStr + " is not a number");
            }

            OpCode opCode = null;

            if ("getstatic".equals(opCodeStr)) {
                opCode = new GetStatic();
            }
            else if ("invokevirtual".equals(opCodeStr)) {
                opCode = new InvokeVirtual();
            }
            else if ("ldc".equals(opCodeStr)) {
                opCode = new Ldc();
            }
            else if  ("imul".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.imul();
            }
            else if  ("iadd".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iadd();
            }
            else if  ("iload_0".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iload_0();
            }
            else if  ("iload_1".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iload_1();
            }
            else if  ("iload_2".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iload_2();
            }
            else if  ("iload_3".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iload_3();
            }
            else if  ("istore_0".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.istore_0();
            }
            else if  ("istore_1".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.istore_1();
            }
            else if  ("istore_2".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.istore_2();
            }
            else if  ("istore_3".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.istore_3();
            }
            else if  ("iconst_0".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iconst_0();
            }
            else if  ("iconst_1".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iconst_1();
            }
            else if  ("iconst_2".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iconst_2();
            }
            else if  ("iconst_3".equals(opCodeStr)) {
                opCode = new SimpleOpCodes.iconst_3();
            }
            else if  ("return".equals(opCodeStr)) {
                opCode = new SimpleOpCodes._return();
            } else {
                throw new Exception("unrecognized opcode " + opCodeStr);
            }

            //The screen shows the offset index, from which the size can be determined. The user should enter the correct index
            //according to the size of that offset.

            //For the first offset the index is always zero
            if(previousOffSetIndex == -1){//-1 is a special value to show we are on the first entry. we can't use zero.
                if (currentOffSetDisplayIndex != 0){
                    throw new Exception("");
                }
                previousOffSetIndex = 0;
            }else{
                int correctDisplayIndex = previousOffSetIndex + previousOffSetSize;

                if (currentOffSetDisplayIndex != correctDisplayIndex){
                    throw new Exception("Offset should be " + correctDisplayIndex + " for " + opCode.getDisplayName());
                }
                previousOffSetIndex = correctDisplayIndex;
            }
            previousOffSetSize = opCode.getExpectedOffSetSize();

            //we don't really need to calculate the size as we can see by the index whether it was correct.
            opCode.checkAndSetArguments(opCode.getExpectedOffSetSize(), currentOffSetDisplayIndex, argStr, commentStr);
            entry.setOpCode(opCode);
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
