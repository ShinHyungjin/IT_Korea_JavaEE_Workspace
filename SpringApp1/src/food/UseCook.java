package food;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UseCook {
	public static void main(String[] args) {
		// ������ �̿�O ����
		ClassPathXmlApplicationContext context = null;
		context = new ClassPathXmlApplicationContext("spring/config/context.xml");
		
		Cook cook = (Cook)context.getBean("cook");
		cook.makeFood();
		
		
		
		/* ������ �̿�x ����
		FriPan pan = new FriPan();
		Cook cook = new Cook();
		
		cook.setPan(pan);
		cook.makeFood();
		*/
	
	}
}
