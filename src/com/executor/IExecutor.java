package com.executor;

import java.io.File;

/**
 * @author Onkar
 *
 */
public interface IExecutor {

	/**
	 * PROGRAM EXECUTION THROUGH DIRECT SOURCE CODE
	 * */
	String execute(String programCode);

	/**
	 * PROGRAM EXECUTION THROUGH SOURCE CODE FILE
	 * */
	String execute(File programFile);

}
