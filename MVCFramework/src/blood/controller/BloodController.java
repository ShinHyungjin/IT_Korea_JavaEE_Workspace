package blood.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.Controller;

import blood.BloodManager;

public class BloodController implements Controller{
	BloodManager manager = new BloodManager();
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String blood = request.getParameter("blood");
		String msg = manager.getAdvisor(blood);
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
	}
	public String getViewPage() {
		return "/blood/blood_result.jsp";
	}
}
