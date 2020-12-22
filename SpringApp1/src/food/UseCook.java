package food;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UseCook {
	public static void main(String[] args) {
		// 스프링 이용O 예시
		ClassPathXmlApplicationContext context = null;
		context = new ClassPathXmlApplicationContext("spring/config/context.xml");
		
		Cook cook = (Cook)context.getBean("cook");
		cook.makeFood();
		
		
		
		/* 스프링 이용x 예시
		FriPan pan = new FriPan();
		Cook cook = new Cook();
		
		cook.setPan(pan);
		cook.makeFood();
		*/
	
	}
}
