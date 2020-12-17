package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.Controller;

import movie.MovieManager;

public class MovieController implements Controller{
	MovieManager manager = new MovieManager();
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movie = request.getParameter("movie");
		String msg = manager.getAdvisor(movie);
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
	}
	public String getViewPage() {
		return "/movie/movie_result.jsp";
	}
}
