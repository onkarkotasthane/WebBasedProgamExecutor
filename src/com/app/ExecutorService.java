package com.app;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.factory.ProgramExecutorFactory;
import com.reference.Language;

/**
 * @author Onkar
 *
 */

/**
 * SERVLET IMPLEMENTATION
 */
@WebServlet("/ExecutorService")
public class ExecutorService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecutorService() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Language requestLanguageType = Language.valueOf(request.getParameter("languageType"));
		String requestProgramFilePath = request.getParameter("programFilePath");
		System.out.println(requestLanguageType + " &" + requestProgramFilePath);
		String output = "";
		switch(requestLanguageType) {
			case JAVA:
				output = ProgramExecutorFactory.getIExecutor(Language.JAVA).execute(new File(requestProgramFilePath));
				System.out.println(output);
				break;
			case PYTHON:
				output = ProgramExecutorFactory.getIExecutor(Language.PYTHON).execute(new File(requestProgramFilePath));
				break;
			default:
				 output = "- Wrong request -";
		}
		response.getWriter().append(output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
