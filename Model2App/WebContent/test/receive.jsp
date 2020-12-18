<%@ page contentType="text/html;charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String msg = request.getParameter("msg");
	
	//session.setAttribute("result", msg);
	request.setAttribute("result", msg);
	
	RequestDispatcher dis = request.getRequestDispatcher("/test/result.jsp");
	
	dis.forward(request, response);
	
%>