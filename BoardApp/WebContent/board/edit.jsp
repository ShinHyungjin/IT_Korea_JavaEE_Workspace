<%@page import="board.model.Notice"%>
<%@page import="board.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="db.DBManager"%>
<%@ page import="java.sql.*"%>
<%@ include file="/inc/lib.jsp"%>
<%
	/*
수정 요청을 처리하는 jsp ... 수정후  상세보기 페이지로 전환할 것이므로, 
디자인이 필요없고 오직 db 로직만 잇으면 됨..
*/
	request.setCharacterEncoding("utf-8");
	String notice_id = request.getParameter("notice_id");
	String author = request.getParameter("author"); //작성자
	String title = request.getParameter("title");//제목
	String content = request.getParameter("content");//내용

	NoticeDAO noticeDAO = new NoticeDAO();
	Notice notice = new Notice();
	
	notice.setAuthor(author);
	notice.setTitle(title);
	notice.setContent(content);
	notice.setNotice_id(Integer.parseInt(notice_id));
	
	int result = noticeDAO.edit(notice);

	if (result == 0) {
		out.print(getMsgBack("수정 실패!"));
	} else {
		out.print(getMsgURL("수정 성공!", "list.jsp"));
	}
%>