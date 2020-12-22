package com.springmvc.comment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.domain.Comment;
import com.model2.model.CommentDAO;

public class RegistController implements Controller{
	CommentDAO commentDAO = new CommentDAO();
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = request.getParameter("msg");
		String author = request.getParameter("author");
		String board_id = request.getParameter("board_id");
		
		Comment comment = new Comment();
		comment.setMsg(msg);
		comment.setAuthor(author);
		comment.setBoard_id(Integer.parseInt(board_id));

		commentDAO.insert(comment);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/comment/list");

		return mav;
	}
}
