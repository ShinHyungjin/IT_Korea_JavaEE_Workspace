<%@page import="board.model.Comments"%>
<%@page import="board.model.CommentsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<% 
		request.setCharacterEncoding("utf-8");

%>
<jsp:useBean id="comments" class="board.model.Comments"></jsp:useBean>
<jsp:setProperty property="*" name="comments"/>
<%
	if(new CommentsDAO().insert(comments)==0) {
		out.print(getMsgBack("댓글 등록실패!"));
	}else {
		out.print(getMsgURL("댓글 등록성공", "detail.jsp?news_id="+comments.getNews_id()));
	}

%>