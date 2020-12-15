package com.greeting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.print.attribute.standard.Severity;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



//서블릿이란 자바 클래스중에서 오직 웹서버에서만 해석 및 실행될 수 있는 클래스
//JSP의 선언부는 Class의 멤버영역, 스크립트는 서비스 메서드 영역에 안착
public class HelloServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("hello servlet do !!");
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		String result = config.getInitParameter("msg");
		System.out.println("init 호출 " + result);
		
		ServletContext context = config.getServletContext();
		System.out.println(context.getRealPath(""));
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("당신이 입력한 id는 " + id);
	}
	public void destroy() {
		System.out.println("저 죽어요");
		super.destroy();
	}
}
