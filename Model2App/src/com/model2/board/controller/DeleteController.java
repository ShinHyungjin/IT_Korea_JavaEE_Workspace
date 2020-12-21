package com.model2.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.model.BoardDAO;

public class DeleteController implements Controller{
	BoardDAO boardDAO = new BoardDAO();
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_id = request.getParameter("board_id");
		
		boardDAO.delete(Integer.parseInt(board_id));
	}

	public String getResultView() {
		return "/view/board/delete";
	}
	public boolean isForwarding() {
		return false;
	}
}
