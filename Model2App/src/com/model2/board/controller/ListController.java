package com.model2.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.domain.Board;
import com.model2.model.BoardDAO;

public class ListController implements Controller {
	BoardDAO boardDAO = new BoardDAO();
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list = boardDAO.selectAll();

		request.setAttribute("boardList", list);
	}

	public String getResultView() {
		return "/view/board/list";
	}

	public boolean isForwarding() {
		return true;
	}
}
