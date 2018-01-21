package com.application;

import java.lang.annotation.Annotation;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.MultipartConfig;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author Onkar
 * @date 2018
 */
public class WebServletConfiguration implements WebApplicationInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet.ServletContext)
	 */
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationContext.register(Application.class);
		annotationConfigWebApplicationContext.setServletContext(servletContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(annotationConfigWebApplicationContext));
		servlet.setLoadOnStartup(1);

		MultipartConfig multipartConfig = new MultipartConfig() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return null;
			}

			@Override
			public long maxRequestSize() {
				return 999999999L;
			}

			@Override
			public long maxFileSize() {
				return 999999999L;
			}

			@Override
			public String location() {
				return null;
			}

			@Override
			public int fileSizeThreshold() {
				return 0;
			}
		};
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(multipartConfig);
		servlet.setMultipartConfig(multipartConfigElement);
		servlet.addMapping("/");
	}
}