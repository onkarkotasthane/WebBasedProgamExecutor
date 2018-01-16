package com.factory;

import com.executor.IExecutor;
import com.executor.JavaIExecutor;
import com.executor.PythonIExecutor;
import com.reference.Language;

/**
 * @author Onkar
 *
 */

/**
 * PROGRAM EXECUTOR FACTORY
 * */
public class ProgramExecutorFactory {
	
	/**
	 * PROGRAM EXECUTION OPTIONS
	 * */
	public static IExecutor getIExecutor(Language language) {
		IExecutor iExecutor = null;
		switch (language) {
			case JAVA:
				iExecutor = new JavaIExecutor();
				break;
			case PYTHON:
				iExecutor = new PythonIExecutor();
				break;
			default:
				break;
		}
		return iExecutor;
	}	
}
