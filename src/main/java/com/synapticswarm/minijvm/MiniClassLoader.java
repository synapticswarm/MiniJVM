package com.synapticswarm.minijvm;

import java.lang.reflect.Field;

public class MiniClassLoader {
	
	/**
	 * TODO doesn't yet deal with $ inner classes
	 *
	 * @param className with slashes not dots
	 * @param fieldName
	 * @return
	 */
	public static Object getStatic(String className, String fieldName){
		try{
		Class<?> loadedClass = Class.forName(className.replace('/', '.'));		
		Field field = loadedClass.getDeclaredField(fieldName);		
		return field.get(null);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
