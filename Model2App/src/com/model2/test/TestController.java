package com.model2.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.controller.Controller;

public class TestController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계 : 하위 컨트롤러별 업무처리
		String msg = "테스트입니다";
		
		//4단계 : 결과저장
		HttpSession session = request.getSession();
		session.setAttribute("result", msg);
	}

	public String getResultView() {
		
		return "/view/test/result";
	}
	public boolean isForwarding() {
		return false;
	}
}
