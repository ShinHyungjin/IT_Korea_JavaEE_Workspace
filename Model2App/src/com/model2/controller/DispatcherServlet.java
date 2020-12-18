package com.model2.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DispatcherServlet extends HttpServlet { // 1�ܰ� : ���� ��Ʈ�ѷ� ����
	FileReader fis; // ��Ʈ�ѷ� ���� ���������� �б����� ��Ʈ��
	JSONObject controllerMap; // ��Ʈ�ѷ��� �������� ����ִ� ��
	JSONObject viewMap; // �� ��Ʈ�ѷ��� view �������� ����ִ� ��

	public void init(ServletConfig config) throws ServletException {
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String realPath = config.getServletContext().getRealPath(contextConfigLocation);
		System.out.println(realPath);
		try {
			fis = new FileReader(realPath);
			JSONParser jsonParser = new JSONParser();
			// �Ľ�
			JSONObject json = (JSONObject) jsonParser.parse(fis);
			// Ű���� ���� ������ ����
			controllerMap = (JSONObject) json.get("controller"); // mapping.json�� �ִ� ��ûuri�� ó���� ����ϴ� controller�� ��ġ
			viewMap = (JSONObject) json.get("view"); // controller�� 3�ܰ� ������ ���� �� �� ����������� �ѱ�� ���� ��������� ������ ���´�

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 2�ܰ� : ��û�� �м�
		String uri = request.getRequestURI(); // uri : ��Ʈ���� �ڿ� �ٴ� �ּ�, url : Full �ּ�

		// if���� ����� ����ȭ�� �����͸� ��������(XML, JSON, Properties ��)
		String controllerName = (String) controllerMap.get(uri);
		System.out.println("��û�� ó���� Class �̸��� : " + controllerName);

		// ���� ��Ʈ�ѷ��� �̸��� �˾����� Ŭ����ȭ �� ��, �ν��Ͻ��� ����� �Լ� ȣ��
		Class controllerClass = null;
		Controller controller = null;
		try {
			controllerClass = Class.forName(controllerName); // JSON���� �Ľ��� *.do uri�� ���ڿ��� Ŭ����ȭ��
			controller = (Controller) controllerClass.newInstance(); // Ŭ����ȭ �� ����� ��üȭ��
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		controller.execute(request, response); // 3�ܰ� : ����
		// ���� ��Ʈ�ѷ��κ��� �Ѱܹ��� �信 ���� ������ �̿��Ͽ� Ŭ���̾�Ʈ���� �˸´� �並 ��������
		String resultKey = controller.getResultView();
		String resultView = (String) viewMap.get(resultKey);

		// ����� sendRedirect�� ó���ؾ� �� ��찡 �ְ�, forwarding���� ó���ؾ� �� ��찡 ����
		if (controller.isForwarding()) {
			RequestDispatcher dis = request.getRequestDispatcher(resultView);
			dis.forward(request, response);
		} else {
			response.sendRedirect(resultView);
		}
	}

	public void destroy() {
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
