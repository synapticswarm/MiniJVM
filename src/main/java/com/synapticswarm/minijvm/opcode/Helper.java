package com.synapticswarm.minijvm.opcode;

//import java.lang.invoke.MethodType;
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
//	public static Class<?> returnTypeClass(String typeDescriptor) {
//		int rightBracket = typeDescriptor.indexOf(')');
//		String returnTypeDescriptor = typeDescriptor.substring(
//				rightBracket + 1, typeDescriptor.length());
//		return parseTypeDescriptorNoBrackets(returnTypeDescriptor).get(0);
//	}

	/**
	 * Parses a whole bytecode type descriptor (e.g. (II)V) and returns a List of
	 * Class<?> Objects representing the parameters.
	 * 
	 * @param typeDescriptor
	 * @return
	 */
//	public static List<Class<?>> parseParameterList(String typeDescriptor) {
//		int leftBracket = typeDescriptor.indexOf('(');
//		int rightBracket = typeDescriptor.indexOf(')');
//		String parameterDescriptor = typeDescriptor.substring(leftBracket + 1,
//				rightBracket);
//		return parseTypeDescriptorNoBrackets(parameterDescriptor);
//	}

	/**
	 * The bytecode method type descriptor is like this: (II)I or ()V
	 * 
	 * this method turns it into a real MethodType object.
	 * 
	 * @param typeDescriptor
	 * @return
	 */
//	public static MethodType resolveMethodType(String typeDescriptor) {
//		List<Class<?>> parameterClassList = parseParameterList(typeDescriptor);
//		Class<?>[] parameterArray = new Class[parameterClassList.size()];
//		Class<?>[] parameterClassArr = parameterClassList.toArray(parameterArray);
//		return MethodType.methodType(returnTypeClass(typeDescriptor),
//				parameterClassArr);
//	}

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
//	public static List<Class<?>> parseTypeDescriptorNoBrackets(String arg) {
//		int index = 0;
//		List<Class<?>> parameters = new LinkedList<Class<?>>();
//
//		while (index < arg.length()) {
//			boolean isRefType = arg.substring(index, index + 1).equals("L");
//
//			if (isRefType) {
//				int semiColonIndex = arg.indexOf(';', index);
//				String refType = arg.substring(index + 1, semiColonIndex);
//				index = semiColonIndex + 1;
//				parameters.add(lookupRefType(refType));
//			} else {
//				String valType = arg.substring(index, index + 1);
//				index = index + 1;
//				parameters.add(lookupValType(valType));
//			}
//		}
//		return parameters;
//	}

	/**
	 * Load a Class with reflection.
	 * 
	 * @param refType fully qualified class name seperated with slashes or dots.
	 * @return
	 */
//	private static Class<?> lookupRefType(String refType) {
//		String withDotsName = refType.replaceAll("/", ".");
//		Class<?> clazz;
//		try {
//			clazz = Class.forName(withDotsName);
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Cannot find class with name "
//					+ withDotsName);
//		}
//		return clazz;
//	}

	/**
	 * Get 2 integers from the constant pool style String e.g. #1.#2
     *
	 * @return an array of type int, with the integers in each of the 2 slots.
	 */
	public static int[] split(String str, String delimiter) {
		int dotIndex = str.indexOf(delimiter);
		int left = Integer.parseInt(str.substring(1, dotIndex));
		int right = Integer.parseInt(str.substring(dotIndex + 2, str.length()));
        return new int [] {left, right};
	}
	
	//strips off the # character if its on the front
	public static String stripLeadingHash(String s){
		if (s.startsWith("#")){
			s = s.substring(1,s.length());
		}
		return s;
	}

    //e.g. converts #3 into 3
    public static int toIntFromSingleValue(String s){
        return Integer.parseInt(Helper.stripLeadingHash(s));
    }

    public static void checkValueIsNullOrEmpty(String arg, String message) throws Exception{
        if (arg != null && arg.length() > 0){
            throw new Exception("Argument " + arg + " should be null for " + message);
        }
    }

    public static boolean checkArgHasTwoIndexes(String arg, String message, String delimiter) throws Exception{

        String delim = null;

        if (".".equals(delimiter)){
            delim = "\\.";
        }
        if (":".equals(delimiter)){
            delim = "\\:";
        }

        if (arg == null || (!arg.matches("#[0-9]{1,2}" + delim + "#[0-9]{1,2}"))){
            throw new Exception("Argument " + arg + " must be of the format #[0-9]{1,2}.#[0-9]{1,2} for " + message);
        }
        return true;
    }

    public static boolean checkArgHasOneIndex(String arg, String message) throws Exception{
        if (arg == null || (!arg.matches("#[0-9]{1,2}"))){
            throw new Exception("Argument " + arg + " must be of the format #[0-9]{1,2} for " + message);
        }
        return true;
    }

    //checks is of format #1 or #12
    protected static void checkArgumentHasValue(String arg, String message) throws Exception{
        if (arg == null || (!arg.matches("#[0-9]{1,2}"))){
            throw new Exception("Argument " + arg + " must be of the format #[0-9]{1,2} for " + message);
        }
    }

}
