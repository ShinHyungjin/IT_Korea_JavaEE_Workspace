package com.model2.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.controller.Controller;
import com.model2.notice.domain.Notice;
import com.model2.notice.model.NoticeDAO;

public class ListController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계 : 알맞는 로직객체에게 일치 시킨다
		List<Notice> list = noticeDAO.selectAll();
		
		//4단계 : 결과값을 저장한다
//		HttpSession session = request.getSession();
//		session.setAttribute("noticeList", list);
		
		request.setAttribute("noticeList", list);
		
		//5단계 : 알맞는 뷰를 보여주는것은 상위컨트롤러의 역할!
	}
	public String getResultView() {
		return "/view/notice/list";
	}
	@Override
	public boolean isForwarding() {
		return true;
	}
}
