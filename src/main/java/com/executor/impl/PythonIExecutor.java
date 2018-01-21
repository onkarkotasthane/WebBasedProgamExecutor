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
public class PythonIExecutor implements IExecutor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.IExecutor#execute(java.lang.String)
	 */
	@Override
	public String execute(String programCode) {
		String tempFileName = "temp" + System.currentTimeMillis();
		File file = new File("E:\\" + tempFileName + ".py");
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
		String programOutput = "";
		try {
			// EXECUTE PROGRAM
			Process process = runtime.exec("python " + programFilePath);
			programOutput += "> EXECUTING" + System.lineSeparator();
			programOutput += programExecutionDetails.show(Language.PYTHON, process);
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
		return null;
	}
}
