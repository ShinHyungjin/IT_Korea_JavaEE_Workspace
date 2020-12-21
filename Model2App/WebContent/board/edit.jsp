<%@page import="com.model2.domain.Board"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Board board = (Board)request.getAttribute("board");
	int result = (int)request.getAttribute("result");

	StringBuilder sb = new StringBuilder();
	sb.append("<script>");
	
	if(result==0) {
		sb.append("alert('수정실패');");
		sb.append("history.back();");
	}else {
		sb.append("alert('수정성공');");
		sb.append("location.href='/board/detail.do?board_id="+board.getBoard_id()+"';");
	}
	sb.append("</script>");
	out.print(sb.toString());
	
%>
