package study;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropTest {
	
	public PropTest() {
		Properties props = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File("D:/IT_Korea_Class/JavaEE/MVCFramework/WebContent/WEB-INF/mapping/mapping.properties"));
			props.load(fis);
			
			System.out.println(props.getProperty("zerg"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	public static void main(String[] args) {
			new PropTest();
	}
}
