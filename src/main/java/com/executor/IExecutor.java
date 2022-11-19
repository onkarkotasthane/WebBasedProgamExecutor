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
	 * @param entryClass (class from where program execution will start)
	 * @return
	 */
	IExecutor entryClass(String entryClass);

}
