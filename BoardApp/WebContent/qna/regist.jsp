<%@page import="board.model.QnADAO"%>
<%@page import="board.model.QnA"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
	
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	QnA qna = new QnA();
	qna.setWriter(writer);
	qna.setTitle(title);
	qna.setContent(content);
	
	QnADAO dao = new QnADAO();
	int result = dao.insert(qna);
	
	if(result == 0) {
		out.print(getMsgBack("등록 실패!"));
	}else {
		out.print(getMsgURL("등록 성공!", "list.jsp"));
	}
%>