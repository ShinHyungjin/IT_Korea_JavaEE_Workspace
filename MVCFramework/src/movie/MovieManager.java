package movie;

public class MovieManager {
	
	public String getAdvisor(String movie) {
		String msg = null;

		if(movie.equals("미션임파서블5")) {
			msg = "미션임파서블5";
		} else if(movie.equals("스타워즈")) {
			msg = "스타워즈";
		}else if(movie.equals("존윅3")) {
			msg = "존윅3";
		}else if(movie.equals("분노의질주")) {
			msg = "분노의질주";
		}
		return msg;
	} 	
}
