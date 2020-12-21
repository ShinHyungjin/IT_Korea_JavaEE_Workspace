package com.model2.comment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.domain.Comment;
import com.model2.model.CommentDAO;

public class ListController implements Controller{
	CommentDAO commentDAO = new CommentDAO();
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_id = request.getParameter("board_id");
		List<Comment> list = commentDAO.selectAll(Integer.parseInt(board_id));
		
		request.setAttribute("commentList", list);
	}

	public String getResultView() {
		return "/view/comment/list";
	}
	public boolean isForwarding() {
		return true;
	}
}
