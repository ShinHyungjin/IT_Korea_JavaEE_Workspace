<%@page import="board.model.QnA"%>
<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/inc/lib.jsp" %>
<%

		QnADAO dao = new QnADAO();
		QnA qna = new QnA();
		
		request.setCharacterEncoding("utf-8");
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String team = request.getParameter("team");
		String rank = request.getParameter("rank");
		String depth = request.getParameter("depth");
		
		qna.setWriter(writer);
		qna.setTitle(title);
		qna.setContent(content);
		qna.setTeam(Integer.parseInt(team));
		qna.setRank(Integer.parseInt(rank));
		qna.setDepth(Integer.parseInt(depth));
		
		int result = dao.reply(qna);
		
		if(result == 0) {
			out.print(getMsgBack("답글등록 실패!"));
		} else {
			out.print(getMsgURL("답글등록 성공!", "list.jsp"));
		}
		
%>