package test;

//강아지의 인스턴스를 오직 1개만 만들도록 조치(=싱글톤 패턴 [GOF - Disign Pattern)

public class Dog {
	String name="뽀미";
	private static Dog instance;

	private Dog() {
		
	}
	
	public static Dog getInstance() {
		if(instance==null) {
			instance = new Dog();
		}
		return instance;
	}
}
