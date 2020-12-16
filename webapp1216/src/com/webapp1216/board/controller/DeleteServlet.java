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

public class DeleteServlet extends HttpServlet{
	NoticeDAO noticeDAO = new NoticeDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notice_id = request.getParameter("notice_id");
		
		int result =  noticeDAO.delete(Integer.parseInt(notice_id));
				
		HttpSession session = request.getSession();

		if(result==0) {
			session.setAttribute("msg", "글이 삭제되지 않았습니다. 관리자에게 문의하세요.");
			response.sendRedirect("/error/message.jsp");
		}else {	
			response.sendRedirect("/board/list");
		}
	}
}
