package com.webapp1216.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp1216.board.model.Notice;
import com.webapp1216.board.model.NoticeDAO;

public class RegistServlet extends HttpServlet{
	NoticeDAO dao = new NoticeDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(notice.getTitle());
		out.print(notice.getWriter());
		out.print(notice.getContent());
		
		int result = dao.insert(notice);
		
		if(result == 0) {
			out.print("<script>");
			out.print("alert('등록실패!');");
			out.print("history.back();");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("alert('등록성공!');");
			out.print("location.href='/board/list';");
			out.print("</script>");
		}
	}
}
