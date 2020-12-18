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
		//3�ܰ� : ����
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		//4�ܰ� : insert�� ��ȯ�� int�� ����� �� ���ִ°����� ����ڸ� ���� ������ �ʿ����
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
