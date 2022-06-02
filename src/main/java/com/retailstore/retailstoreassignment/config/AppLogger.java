package com.retailstore.retailstoreassignment.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Loggers are instantiated as static instead of the instance level to use memory efficiently
 */
public class AppLogger {
	private static Map<Class,Logger> loggerMap = new HashMap<>();

	public static Logger getLogger(Class clzz){
		if (!loggerMap.containsKey(clzz)){
			loggerMap.put(clzz, LoggerFactory.getLogger(clzz));
		}
		return loggerMap.get(clzz);
	}
}
