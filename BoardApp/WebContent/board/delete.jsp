<%@page import="board.model.Notice"%>
<%@page import="board.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="db.DBManager"%>
<%@ page import="java.sql.*"%>
<%@ include file="/inc/lib.jsp"%>
<%

	String notice_id = request.getParameter("notice_id");

	NoticeDAO noticeDAO = new NoticeDAO();
	Notice notice = new Notice();
	
	notice = noticeDAO.select(Integer.parseInt(notice_id));
	int result = noticeDAO.del(notice);
	
	if(result==0){
		out.print(getMsgBack("삭제 실패!"));
	}else{
		out.print(getMsgURL("삭제 성공!", "list.jsp"));
	}	
	
%>