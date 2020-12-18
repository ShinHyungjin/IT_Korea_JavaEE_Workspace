package com.model2.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.notice.domain.Notice;
import com.model2.notice.model.NoticeDAO;

public class RegistController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Notice notice = new Notice();
		//3단계 : 업무
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		//4단계 : insert의 반환값 int는 결과만 알 수있는것이지 사용자를 위한 저장은 필요없다
		noticeDAO.insert(notice);
	}

	public String getResultView() {
		return "/view/notice/regist";
	}

	@Override
	public boolean isForwarding() {
		return false;
	}
}
