<%@page import="board.model.QnA"%>
<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="db.DBManager"%>
<%@ page import="java.sql.*"%>
<%@ include file="/inc/lib.jsp"%>
<%

	String qna_id = request.getParameter("qna_id");

	QnADAO dao = new QnADAO();
	QnA qna  = new QnA();
	
	qna = dao.select(Integer.parseInt(qna_id));
	int result = dao.delete(qna);
	
	if(result==0){
		out.print(getMsgBack("삭제 실패!"));
	}else{
		out.print(getMsgURL("삭제 성공!", "list.jsp"));
	}	
	
%>