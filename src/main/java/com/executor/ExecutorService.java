package com.executor;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.executor.factor.ProgramExecutorFactory;
import com.reference.Input;
import com.reference.Language;

/**
 * @author Onkar
 * @date 2018
 */
@Controller
public class ExecutorService {
	/**
	 * @param requestData
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @throws IOException
	 */
	@RequestMapping(path = "ExecutorService", method = RequestMethod.POST, consumes = "application/json")
	public void greet(@RequestBody String requestData, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

		JSONObject requestJsonData = new JSONObject(requestData);

		// LANGUAGE TYPE
		Language requestLanguageType = Language.valueOf(requestJsonData.getString("languageType"));

		// Source or file
		Input requestInputType = Input.valueOf(requestJsonData.getString("inputType"));

		// SOURCE Data/ FILE
		String requestInput = requestJsonData.getString("input");

		String output = "";
		switch (requestLanguageType) {
		case JAVA:
			switch (requestInputType) {
			case SOURCE:
				output = ProgramExecutorFactory.getIExecutor(Language.JAVA).entryClass(requestJsonData.getString("class")).execute(requestInput);
				break;
			case FILE_PATH:
				output = ProgramExecutorFactory.getIExecutor(Language.JAVA).execute(new File(requestInput));
				break;
			default:
				break;
			}
			break;
		case PYTHON:
			switch (requestInputType) {
			case SOURCE:
				output = ProgramExecutorFactory.getIExecutor(Language.PYTHON).execute(requestInput);
				break;
			case FILE_PATH:
				output = ProgramExecutorFactory.getIExecutor(Language.PYTHON).execute(new File(requestInput));
				break;
			default:
				break;
			}
			break;
		default:
			output = "- Wrong request -";
		}
		httpServletResponse.getWriter().append(output);
	}
}
