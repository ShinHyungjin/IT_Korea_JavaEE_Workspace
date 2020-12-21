package com.model2.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.domain.Board;
import com.model2.model.BoardDAO;

public class EditController implements Controller{
	BoardDAO boardDAO = new BoardDAO();
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_id = request.getParameter("board_id");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Board board = new Board();
		board.setBoard_id(Integer.parseInt(board_id));
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		int result = boardDAO.update(board);
		
		request.setAttribute("board", board);
		request.setAttribute("result", result);
	}

	public String getResultView() {
		return "/view/board/edit";
	}

	public boolean isForwarding() {
		return true;
	}

}
