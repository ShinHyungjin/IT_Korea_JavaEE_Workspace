package com.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	FileInputStream fis;
	Properties props;
	
	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext(); // JSP가 아니여서 application 객체가 아닌 ServletContext를 써야함
		String savePath = context.getRealPath(config.getInitParameter("contextConfigLocation"));
		try {
			fis = new FileInputStream(savePath);
			props = new Properties();
			props.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}

	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			Controller controller = null;;
			String className = null;
			String uri = request.getRequestURI();

			className = props.getProperty(uri);
								
			Class controllerClass;
			try {
				controllerClass = Class.forName(className);
				controller = (Controller)controllerClass.newInstance();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
			controller.execute(request, response);
			response.sendRedirect(controller.getViewPage());
		}
	
	public void destroy() {
		if(fis!=null)
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
