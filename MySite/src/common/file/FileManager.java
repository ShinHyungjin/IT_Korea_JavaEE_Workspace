package common.file;

public class FileManager {
	public static String getExtend(String path) {
		int last = path.lastIndexOf(".");
		String file = path.substring(last+1, path.length());
		
		return file;
	}
	
//	public static void main(String[] args) {
//		String filename="D:\\photo\\summer\\2010\\지난.여름에.놀러.갔던.사진.jpg";
//		getExtend(filename);
//	}
	
}
