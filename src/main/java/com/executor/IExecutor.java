package com.executor;

import java.io.File;

/**
 * @author Onkar
 * @date 2018
 */
public interface IExecutor {

	/**
	 * @param programCode
	 * @return
	 */
	String execute(String programCode);

	/**
	 * @param programFile
	 * @return
	 */
	String execute(File programFile);

	/**
	 * @param entryClass
	 * @return
	 */
	IExecutor entryClass(String entryClass);

}
