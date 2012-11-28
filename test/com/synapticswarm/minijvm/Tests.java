package com.synapticswarm.minijvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import junit.framework.Assert;

import org.junit.Test;

import com.synapticswarm.minijvm.model.AbstractConstantPoolType;
import com.synapticswarm.minijvm.model.CPClass;
import com.synapticswarm.minijvm.model.CPFieldref;
import com.synapticswarm.minijvm.model.CPMethodref;
import com.synapticswarm.minijvm.model.CPNameAndType;
import com.synapticswarm.minijvm.model.CPUtf8;
import com.synapticswarm.minijvm.model.MiniConstantPool;
import com.synapticswarm.minijvm.model.MiniMethodEntry;
import com.synapticswarm.minijvm.opcode.GetStatic;
import com.synapticswarm.minijvm.opcode.Helper;
import com.synapticswarm.minijvm.opcode.InvokeVirtual;
import com.synapticswarm.minijvm.ui.model.StackEntryDisplayModel;


public class Tests {
	
	public static class InvokeTester{
		int count = 2;
		
		public void add(Integer i){
			count+=i;
		}
	}
	
	@Test
	public void testInvokeVirtual() {
		ObservableList <StackEntryDisplayModel> observableList = FXCollections.observableArrayList();
		ObservableStack stack = new ObservableStack(observableList);
		

		InvokeTester testObject = new InvokeTester();
		
		stack.push(testObject);
		stack.push(new Integer(3));		
		
		MiniConstantPool mcp = new MiniConstantPool(3);
		AbstractConstantPoolType [] entries = mcp.getEntries();
		
		entries[1] = new CPMethodref(-1, 2);
		entries [2] = new CPNameAndType(3, -1);
		entries [3] = new CPUtf8("add");
		
		InvokeVirtual invokeVirtual = new InvokeVirtual(1);
		invokeVirtual.execute(stack, mcp, null);
		
		Assert.assertEquals(testObject.count, new Integer(5), testObject.count);
	}
	
	@Test
	public void testReflection() throws Exception{
		String className = "java/lang/System";
		String fieldName = "out";
		String fieldType = "java/io/PrintWriter";
		
		Class loadedClass = Class.forName(className.replace('/', '.'));
		
		Field field = loadedClass.getDeclaredField(fieldName);
		
		System.out.println(field.getType());
		Assert.assertEquals(System.out, field.get(null));
		
	}
	
	@Test
	public void testMiniClassLoader_staticField(){
		Object systemOut = MiniClassLoader.getStatic("java/lang/System", "out");
		Assert.assertEquals(systemOut, System.out);
	}
	
	//More elaborate. Build up mock objects.
//	@Test
//	public void testGetStatic(){
//		
//		JVM jvm = new JVM();
//		
//		MiniMethodEntry currentEntry = new MiniMethodEntry();
//		currentEntry.arg = 16;
//		
//		MiniConstantPool constantPool = new MiniConstantPool(21);
//		constantPool.set(16, new CPFieldref(17,19));
//		constantPool.set(17, new CPClass(18));
//		constantPool.set(18, new CPUtf8("java/lang/System"));
//		constantPool.set(19, new CPNameAndType(20,21));
//		constantPool.set(20, new CPUtf8("out"));
//		constantPool.set(21, new CPUtf8("Ljava/io/PrintStream"));
//		
//		GetStatic target = new GetStatic();
//		target.execute(jvm, null, currentEntry, constantPool);
//		
//		Assert.assertEquals(System.out, jvm.getStack().pop());
//	}
//	
//	public static class TestTarget{
//		public static boolean didSomething = false;
//		
//		public static void doSomething(String s){
//			didSomething = true;
//		}
//	}
	
//	@Test
//	public void testInvokeVirtual(){
//		
//		JVM jvm = new JVM();
//		
//		MiniMethodEntry currentEntry = new MiniMethodEntry();
//		currentEntry.arg = 24;
//
//		MiniConstantPool constantPool = new MiniConstantPool(29);
//		constantPool.set(24, new CPMethodref(25, 27));
//		constantPool.set(25, new CPClass(26));
//		constantPool.set(26, new CPUtf8("com/synapticswarm/minijvm/Tests$TestTarget"));
//		constantPool.set(27, new CPNameAndType(28,29));
//		constantPool.set(28, new CPUtf8("doSomething"));
//		constantPool.set(29, new CPUtf8("(Ljava/lang/String;)V"));
//		
//		//method will expect a String so push one on the stack
//		jvm.getStack().push("hello");
//		
//		//this is a bit weird as the target call is for a static method but we still need to push the
//		//target object on the stack to be consistent for later tests.
//		TestTarget testTarget = new TestTarget();
//		jvm.getStack().push(testTarget);
//		
//		InvokeVirtual iv = new InvokeVirtual();
//		iv.execute(jvm, null, currentEntry, constantPool);
//		
//		Assert.assertTrue(TestTarget.didSomething);
//	}
	
//	@Test
//	public void testMethodHandle() throws Throwable{
//
//		TestTarget testTarget = new TestTarget();
//		
//		MethodHandles.Lookup lookup = MethodHandles.lookup();
//		MethodType methodType = Helper.resolveMethodType("(Ljava/lang/String;)V");
//		MethodHandle targetMethod  = lookup.findStatic(testTarget.getClass(),
//				"doSomething", methodType);
//		
//		targetMethod.invoke(null);
//	}
	
	@Test
	public void testHelper(){
		List <Class <?>> expectedParameterClassList = new ArrayList <>();
		expectedParameterClassList.add(String.class);
		testTypeDescriptor("(Ljava/lang/String;)V",expectedParameterClassList, void.class );
		
		expectedParameterClassList.clear();
		expectedParameterClassList.add(int.class);
		expectedParameterClassList.add(int.class);
		testTypeDescriptor("(II)I",expectedParameterClassList, int.class );

		expectedParameterClassList.clear();
		expectedParameterClassList.add(String.class);
		expectedParameterClassList.add(int.class);
		testTypeDescriptor("(Ljava/lang/String;I)V",expectedParameterClassList, void.class );

		expectedParameterClassList.clear();
		expectedParameterClassList.add(float.class);
		testTypeDescriptor("(F)Ljava/lang/Float;",expectedParameterClassList, Float.class );
	}
	
	private void testTypeDescriptor(String typeDescriptor, List <Class<?>> expectedParameterClassList, Class<?> expectedRetTypeClass){
		List <Class<?>> parameterClassList = Helper.parseParameterList(typeDescriptor);
		Assert.assertEquals(expectedParameterClassList, parameterClassList);
		
		Class <?> retTypeClass = Helper.returnTypeClass(typeDescriptor);
		Assert.assertEquals(expectedRetTypeClass, retTypeClass);
	}
}






