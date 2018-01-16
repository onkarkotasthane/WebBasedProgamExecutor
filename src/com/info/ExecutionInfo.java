package com.info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.reference.Language;

/**
 * @author Onkar
 *
 */
public class ExecutionInfo {
	
	/**
	 * SHOW PROGAM COMPILATION & EXECUTION OPERATION OUTPUT (OUTPUT & ERRORS)
	 * */
	public String show(Language language, Process process) {
		String executionOutput = "";
		try {
			BufferedReader executionOutputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader executionErroStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			executionOutput += "Excecuting " + language.toString() + System.lineSeparator();
			
			/*
			 * PRINTING PROGRAM COMPILATION/EXECUTION OUTPUT 
			 * */
			executionOutput += "Output:" + System.lineSeparator();
			String s = null;
			while ((s = executionOutputStream.readLine()) != null) {
				// System.out.println(s);
				executionOutput += s + System.lineSeparator();
			}
			
			/*
			 * PRINTING PROGRAM COMPILATION/EXECUTION ERRORS 
			 * */
			executionOutput += "Error (if any):"+ System.lineSeparator();
			while ((s = executionErroStream.readLine()) != null) {
				//System.out.println(s);
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
