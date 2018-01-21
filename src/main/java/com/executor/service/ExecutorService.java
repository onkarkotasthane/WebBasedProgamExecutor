package com.executor.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.executor.factory.ProgramExecutorFactory;
import com.reference.Language;

/**
 * @author Onkar
 * @date 2018
 */
@Controller
public class ExecutorService {

	/**
	 * @param sourceFile
	 * @param requestData
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @throws IOException
	 */
	@RequestMapping(path = "ExecutorServiceFile", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public void executorServiceFile(@RequestPart MultipartFile sourceFile, @RequestPart String requestData, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

		// SAVE FILE
		File file = new File("E:\\" + sourceFile.getOriginalFilename());
		sourceFile.transferTo(file);

		JSONObject requestJsonData = new JSONObject(requestData);
		// LANGUAGE TYPE
		Language requestLanguageType = Language.valueOf(requestJsonData.getString("languageType"));

		String output = "";
		switch (requestLanguageType) {
			case JAVA:
				output = ProgramExecutorFactory.getIExecutor(Language.JAVA).execute(file);
				break;
			case PYTHON:
				output = ProgramExecutorFactory.getIExecutor(Language.PYTHON).execute(file);
				break;
			default:
				output = "- Wrong request -";
		}
		httpServletResponse.getWriter().append(output);
	}

	/**
	 * @param requestData
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @throws IOException
	 */
	@RequestMapping(path = "ExecutorServiceSource", method = RequestMethod.POST, consumes = "application/json")
	public void executorServiceSource(@RequestBody String requestData, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

		JSONObject requestJsonData = new JSONObject(requestData);

		// LANGUAGE TYPE
		Language requestLanguageType = Language.valueOf(requestJsonData.getString("languageType"));

		// SOURCE Data/ FILE
		String requestInput = requestJsonData.getString("input");

		String output = "";
		switch (requestLanguageType) {
			case JAVA:
				output = ProgramExecutorFactory.getIExecutor(Language.JAVA).entryClass(requestJsonData.getString("class")).execute(requestInput);
				break;
			case PYTHON:
				output = ProgramExecutorFactory.getIExecutor(Language.PYTHON).execute(requestInput);
				break;
			default:
				output = "- Wrong request -";
		}
		httpServletResponse.getWriter().append(output);
	}
}
