package movie;

public class MovieManager {
	
	public String getAdvisor(String movie) {
		String msg = null;

		if(movie.equals("�̼����ļ���5")) {
			msg = "�̼����ļ���5";
		} else if(movie.equals("��Ÿ����")) {
			msg = "��Ÿ����";
		}else if(movie.equals("����3")) {
			msg = "����3";
		}else if(movie.equals("�г�������")) {
			msg = "�г�������";
		}
		return msg;
	} 	
}
