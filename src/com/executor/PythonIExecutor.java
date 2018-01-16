package com.executor;

import java.io.File;
import java.io.IOException;

import com.info.ExecutionInfo;
import com.reference.Language;

/**
 * @author Onkar
 *
 */
public class PythonIExecutor implements IExecutor {

	/**
	 * PROGRAM EXECUTION THROUGH DIRECT SOURCE CODE
	 * */
	@Override
	public String execute(String programCode) {
		System.out.println("Impl unavilable");
		return "Impl not available";
	}

	/**
	 * PROGRAM EXECUTION THROUGH SOURCE CODE FILE
	 * */
	@Override
	public String execute(File programFile) {
		ExecutionInfo programExecutionDetails = new ExecutionInfo();
		Runtime runtime = Runtime.getRuntime();
		String programFilePath = programFile.getAbsolutePath();
		String programOutput = "";
		try {
			// EXECUTE PROGRAM
			Process process = runtime.exec("python " + programFilePath);
			programOutput += programExecutionDetails.show(Language.PYTHON, process);
			
		} catch (IOException e) {
			programOutput += "Server execution error";
			e.printStackTrace();
		}
		return programOutput;
	}

}
