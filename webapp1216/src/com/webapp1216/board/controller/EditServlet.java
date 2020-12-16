package com.webapp1216.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webapp1216.board.model.Notice;
import com.webapp1216.board.model.NoticeDAO;

public class EditServlet extends HttpServlet{
	NoticeDAO noticeDAO = new NoticeDAO();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String notice_id = request.getParameter("notice_id");
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		notice.setNotice_id(Integer.parseInt(notice_id));
		
		int result =  noticeDAO.update(notice);
		HttpSession session = request.getSession();

		if(result==0) {
			session.setAttribute("msg", "���� �������� �ʾҽ��ϴ�. �����ڿ��� �����ϼ���.");
			response.sendRedirect("/error/message.jsp");
		}else {	
			response.sendRedirect("/board/detail?notice_id="+notice.getNotice_id());
		}
	}
}
