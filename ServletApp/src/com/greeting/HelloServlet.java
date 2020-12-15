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



//�����̶� �ڹ� Ŭ�����߿��� ���� ������������ �ؼ� �� ����� �� �ִ� Ŭ����
//JSP�� ����δ� Class�� �������, ��ũ��Ʈ�� ���� �޼��� ������ ����
public class HelloServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("hello servlet do !!");
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		String result = config.getInitParameter("msg");
		System.out.println("init ȣ�� " + result);
		
		ServletContext context = config.getServletContext();
		System.out.println(context.getRealPath(""));
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("����� �Է��� id�� " + id);
	}
	public void destroy() {
		System.out.println("�� �׾��");
		super.destroy();
	}
}
