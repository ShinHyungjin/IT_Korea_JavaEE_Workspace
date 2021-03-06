<%@page import="board.model.ImageBoardDAO"%>
<%@page import="board.model.ImageBoard"%>
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
	String board_id = request.getParameter("board_id");
	String author = request.getParameter("author"); //작성자
	String title = request.getParameter("title");//제목
	String content = request.getParameter("content");//내용
	String filename = request.getParameter("filename");//내용

	ImageBoardDAO dao = new ImageBoardDAO();
	ImageBoard imageBoard = new ImageBoard();
	
	imageBoard.setAuthor(author);
	imageBoard.setTitle(title);
	imageBoard.setContent(content);
	imageBoard.setFilename(filename);
	imageBoard.setBoard_id(Integer.parseInt(board_id));
	
	int result = dao.update(imageBoard);

	if (result == 0) {
		out.print(getMsgBack("수정 실패!"));
	} else {
		out.print(getMsgURL("수정 성공!", "list.jsp"));
	}
%>