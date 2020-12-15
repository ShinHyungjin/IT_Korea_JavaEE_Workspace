package common.file;

import java.io.File;

public class FileManager {
	public static String getExtend(String path) {
		int last = path.lastIndexOf(".");
		String file = path.substring(last+1, path.length());
		
		return file;
	}
	public static boolean deleteFile(String path) {
		File file = new File(path);
		return file.delete();
	}
	
//	public static void main(String[] args) {
//		String filename="D:\\photo\\summer\\2010\\����.������.�.����.����.jpg";
//		getExtend(filename);
//	}
	
}
