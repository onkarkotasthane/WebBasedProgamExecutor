package com.executor.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.executor.IExecutor;
import com.executor.info.ExecutionInfo;
import com.reference.Language;

/**
 * @author Onkar
 * @date 2018
 */
@Component
public class JavaIExecutor implements IExecutor {

	private String entryClass;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.IExecutor#execute(java.lang.String)
	 */
	@Override
	public String execute(String programCode) {

		String tempFileName = this.entryClass;
		File file = new File("E:\\" + tempFileName + ".java");
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			fileWriter.write(programCode);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.execute(file);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.IExecutor#execute(java.io.File)
	 */
	@Override
	public String execute(File programFile) {
		ExecutionInfo programExecutionDetails = new ExecutionInfo();
		Runtime runtime = Runtime.getRuntime();
		String programFilePath = programFile.getAbsolutePath();
		String classPath = programFilePath.substring(0, programFilePath.lastIndexOf("\\"));
		String classFile = programFilePath.substring(programFilePath.lastIndexOf("\\") + 1, programFilePath.lastIndexOf("."));
		String programOutput = "";
		try {
			// COMPILE PROGRAM
			Process process = runtime.exec("javac " + programFilePath);
			System.out.println("> COMPILE PROGRAM");
			programOutput += programExecutionDetails.show(Language.JAVA, process);

			// EXECUTE PROGRAM
			System.out.println("> EXECUTE PROGRAM");
			process = runtime.exec("java -cp " + classPath + " " + classFile);
			programOutput += programExecutionDetails.show(Language.JAVA, process);

		} catch (IOException e) {
			programOutput += "Server execution error";
			e.printStackTrace();
		}
		return programOutput;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.IExecutor#entryClass(java.lang.String)
	 */
	@Override
	public IExecutor entryClass(String entryClass) {
		this.entryClass = entryClass;
		return this;
	}

}
