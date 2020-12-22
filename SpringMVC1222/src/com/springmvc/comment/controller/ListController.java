package com.springmvc.comment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.domain.Comment;
import com.model2.model.CommentDAO;

public class ListController implements Controller{
	CommentDAO commentDAO = new CommentDAO();
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String board_id = request.getParameter("board_id");
		
		List<Comment> commentList = commentDAO.selectAll(Integer.parseInt(board_id));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("commentList", commentList);
		mav.setViewName("/comment/list");

		return mav;
	}

}
