<%@page import="board.model.ImageBoard"%>
<%@page import="board.model.ImageBoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="db.DBManager"%>
<%@ page import="java.sql.*"%>
<%@ include file="/inc/lib.jsp"%>
<%

	String board_id = request.getParameter("board_id");

	ImageBoardDAO dao = new ImageBoardDAO();
	ImageBoard imageBoard = new ImageBoard();
	
	imageBoard = dao.select(Integer.parseInt(board_id));
	int result = dao.delete(imageBoard);
	
	if(result==0){
		out.print(getMsgBack("삭제 실패!"));
	}else{
		out.print(getMsgURL("삭제 성공!", "list.jsp"));
	}	
	
%>