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

public class DispatcherServlet extends HttpServlet { // 1단계 : 상위 컨트롤러 생성
	FileReader fis; // 컨트롤러 매핑 설정파일을 읽기위한 스트림
	JSONObject controllerMap; // 컨트롤러의 정보들이 들어있는 맵
	JSONObject viewMap; // 각 컨트롤러별 view 정보들이 들어있는 맵

	public void init(ServletConfig config) throws ServletException {
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String realPath = config.getServletContext().getRealPath(contextConfigLocation);
		System.out.println(realPath);
		try {
			fis = new FileReader(realPath);
			JSONParser jsonParser = new JSONParser();
			// 파싱
			JSONObject json = (JSONObject) jsonParser.parse(fis);
			// 키값을 통한 데이터 접근
			controllerMap = (JSONObject) json.get("controller"); // mapping.json에 있는 요청uri의 처리를 담당하는 controller의 위치
			viewMap = (JSONObject) json.get("view"); // controller의 3단계 업무가 종료 된 후 결과페이지로 넘기기 위한 결과페이지 정보를 갖는다

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
		// 2단계 : 요청을 분석
		String uri = request.getRequestURI(); // uri : 루트기준 뒤에 붙는 주소, url : Full 주소

		// if문을 대신할 구조화된 데이터를 선택하자(XML, JSON, Properties 등)
		String controllerName = (String) controllerMap.get(uri);
		System.out.println("요청을 처리할 Class 이름은 : " + controllerName);

		// 하위 컨트롤러의 이름을 알았으니 클래스화 한 후, 인스턴스를 만들고 함수 호출
		Class controllerClass = null;
		Controller controller = null;
		try {
			controllerClass = Class.forName(controllerName); // JSON으로 파싱한 *.do uri의 문자열을 클래스화함
			controller = (Controller) controllerClass.newInstance(); // 클래스화 된 대상을 객체화함
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		controller.execute(request, response); // 3단계 : 업무
		// 하위 컨트롤러로부터 넘겨받은 뷰에 대한 정보를 이용하여 클라이언트에게 알맞는 뷰를 보여주자
		String resultKey = controller.getResultView();
		String resultView = (String) viewMap.get(resultKey);

		// 응답시 sendRedirect로 처리해야 할 경우가 있고, forwarding으로 처리해야 할 경우가 있음
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
