package com.executor;

import java.io.File;
import java.io.IOException;

import com.info.ExecutionInfo;
import com.reference.Language;

/**
 * @author Onkar
 *
 */
public class JavaIExecutor implements IExecutor {

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
		String classPath =  programFilePath.substring(0, programFilePath.lastIndexOf("\\"));
		String classFile =  programFilePath.substring(programFilePath.lastIndexOf("\\") + 1, programFilePath.lastIndexOf("."));
		String programOutput = "";
		try {
			// 1. COMPILE PROGRAM
			Process process = runtime.exec("javac " + programFilePath);
			System.out.println("> COMPILE PROGRAM");
			programOutput += programExecutionDetails.show(Language.JAVA, process);

			// 2. EXECUTE PROGRAM
			System.out.println("> EXECUTE PROGRAM");
			process = runtime.exec("java -cp " + classPath + " " + classFile);
			programOutput += programExecutionDetails.show(Language.JAVA, process);
			
			
		} catch (IOException e) {
			programOutput += "Server execution error";
			e.printStackTrace();
		}
		return programOutput;
	}
}
