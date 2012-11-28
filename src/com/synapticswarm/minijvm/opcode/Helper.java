package com.synapticswarm.minijvm.opcode;

import java.lang.invoke.MethodType;
import java.util.LinkedList;
import java.util.List;

public class Helper {
	
	/**
	 * Parses a whole bytecode type descriptor (e.g. (II)V) and returns a Class<?>
	 * Object representing the method return type. NB: remember 'void' has a Class object!
	 * 
	 * @param typeDescriptor
	 * @return
	 */
	public static Class<?> returnTypeClass(String typeDescriptor) {
		int rightBracket = typeDescriptor.indexOf(')');
		String returnTypeDescriptor = typeDescriptor.substring(
				rightBracket + 1, typeDescriptor.length());
		return parseTypeDescriptorNoBrackets(returnTypeDescriptor).get(0);
	}

	/**
	 * Parses a whole bytecode type descriptor (e.g. (II)V) and returns a List of
	 * Class<?> Objects representing the parameters.
	 * 
	 * @param typeDescriptor
	 * @return
	 */
	public static List<Class<?>> parseParameterList(String typeDescriptor) {
		int leftBracket = typeDescriptor.indexOf('(');
		int rightBracket = typeDescriptor.indexOf(')');
		String parameterDescriptor = typeDescriptor.substring(leftBracket + 1,
				rightBracket);
		return parseTypeDescriptorNoBrackets(parameterDescriptor);
	}

	/**
	 * The bytecode method type descriptor is like this: (II)I or ()V
	 * 
	 * this method turns it into a real MethodType object.
	 * 
	 * @param typeDescriptor
	 * @return
	 */
	public static MethodType resolveMethodType(String typeDescriptor) {
		List<Class<?>> parameterClassList = parseParameterList(typeDescriptor);
		Class<?>[] parameterArray = new Class[parameterClassList.size()];
		Class<?>[] parameterClassArr = parameterClassList.toArray(parameterArray);
		return MethodType.methodType(returnTypeClass(typeDescriptor),
				parameterClassArr);
	}

	/**
	 * Parses a bytecode type descriptor into a List of appropriate Class
	 * objects.
	 * 
	 * A whole bytecode method type descriptor is like this:
	 * 
	 * (II)I or ()V or (Ljava/lang/String;Ljava/lang/String;)V etcetera
	 * 
	 * So a type descriptor is the bit either inbetween the brackets or to the
	 * right of the ')' bracket.
	 * 
	 * @param arg
	 * @return
	 */
	public static List<Class<?>> parseTypeDescriptorNoBrackets(String arg) {
		int index = 0;
		List<Class<?>> parameters = new LinkedList<>();

		while (index < arg.length()) {
			boolean isRefType = arg.substring(index, index + 1).equals("L");

			if (isRefType) {
				int semiColonIndex = arg.indexOf(';', index);
				String refType = arg.substring(index + 1, semiColonIndex);
				index = semiColonIndex + 1;
				parameters.add(lookupRefType(refType));
			} else {
				String valType = arg.substring(index, index + 1);
				index = index + 1;
				parameters.add(lookupValType(valType));
			}
		}
		return parameters;
	}

	/**
	 * Load a Class with reflection.
	 * 
	 * @param refType fully qualified class name seperated with slashes or dots.
	 * @return
	 */
	private static Class<?> lookupRefType(String refType) {
		String withDotsName = refType.replaceAll("/", ".");
		Class<?> clazz;
		try {
			clazz = Class.forName(withDotsName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Cannot find class with name "
					+ withDotsName);
		}
		return clazz;
	}

	/**
	 * Get the Class for the supplied bytecode type code.
	 * 
	 * @param valType A code representing a primitive type e.g. 'B' for byte.
	 * @return
	 */
	private static Class<?> lookupValType(String valType) {
		switch (valType) {
		case "B":
			return byte.class;
		case "C":
			return char.class;
		case "D":
			return double.class;
		case "F":
			return float.class;
		case "I":
			return int.class;
		case "J":
			return long.class;
		case "S":
			return short.class;
		case "Z":
			return boolean.class;
		case "V":
			return void.class;
		case "[":
			throw new RuntimeException("array types not yet supported");
		default:
			throw new RuntimeException("Unrecognized value type code " + valType);
		}
	}

	public static int[] splitDotDelimited(String str) {
		return split(str, '.');
	}

	public static int[] splitColonDelimited(String str) {
		return split(str, ':');
	}

	/**
	 * Get 2 integers from the constant pool style String e.g. #1.#2
	 * 
	 * @param str
	 * @param delim
	 * @return an array of type int, with the integers in each of the 2 slots.
	 */
	public static int[] split(String str, char delim) {
		int[] ints = new int[2];
		int dotIndex = str.indexOf(delim);
		ints[0] = Integer.parseInt(str.substring(1, dotIndex));
		ints[1] = Integer.parseInt(str.substring(dotIndex + 2, str.length()));
		return ints;
	}
	
	//strips off the # character if its on the front
	public static String stripLeadingHash(String s){
		if (s.startsWith("#")){
			s = s.substring(1,s.length());
		}
		return s;
	}
}
