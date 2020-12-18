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
		//3�ܰ� : �˸´� ������ü���� ��ġ ��Ų��
		List<Notice> list = noticeDAO.selectAll();
		
		//4�ܰ� : ������� �����Ѵ�
//		HttpSession session = request.getSession();
//		session.setAttribute("noticeList", list);
		
		request.setAttribute("noticeList", list);
		
		//5�ܰ� : �˸´� �並 �����ִ°��� ������Ʈ�ѷ��� ����!
	}
	public String getResultView() {
		return "/view/notice/list";
	}
	@Override
	public boolean isForwarding() {
		return true;
	}
}
