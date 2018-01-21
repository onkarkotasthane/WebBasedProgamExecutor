package com.executor.factory;

import com.executor.IExecutor;
import com.executor.impl.JavaIExecutor;
import com.executor.impl.PythonIExecutor;
import com.reference.Language;

/**
 * @author Onkar
 * @date 2018
 */
public class ProgramExecutorFactory<T> {

	/**
	 * @param language
	 * @return
	 */
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
