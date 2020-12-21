package com.model2.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.domain.Board;
import com.model2.model.BoardDAO;

public class DetailController implements Controller{
	BoardDAO boardDAO = new BoardDAO();
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_id = request.getParameter("board_id");
		Board board = boardDAO.select(Integer.parseInt(board_id));

		request.setAttribute("board", board);		
	}
	public String getResultView() {
		return "/view/board/detail";
	}

	public boolean isForwarding() {
		return true;
	}
}
