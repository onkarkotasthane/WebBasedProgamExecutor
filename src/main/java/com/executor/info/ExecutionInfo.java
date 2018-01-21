package com.executor.info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

import com.reference.Language;

/**
 * @author Onkar
 * @date 2018
 */
@Component
public class ExecutionInfo {

	/**
	 * @param language
	 * @param process
	 * @return
	 */
	public String show(Language language, Process process) {
		String executionOutput = "";
		try {
			BufferedReader executionOutputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader executionErroStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			/*
			 * PRINTING PROGRAM COMPILATION/EXECUTION OUTPUT
			 */
			executionOutput += "Output: " + System.lineSeparator();
			String s = null;
			while ((s = executionOutputStream.readLine()) != null) {
				// System.out.println(s);
				executionOutput += s + System.lineSeparator();
			}

			/*
			 * PRINTING PROGRAM COMPILATION/EXECUTION ERRORS
			 */
			executionOutput += "Error (if any):" + System.lineSeparator();
			while ((s = executionErroStream.readLine()) != null) {
				// System.out.println(s);
				executionOutput += s + System.lineSeparator();
			}
			return executionOutput;
		} catch (IOException e) {
			executionOutput += "Service execution error";
			e.printStackTrace();
		}
		return "";
	}
}
