package com.springmvc.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class TestController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = "¾È³ç";

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);

		mav.setViewName("test/result");

		return mav;
	}

}
