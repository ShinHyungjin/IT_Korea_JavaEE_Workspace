package test;

//�������� �ν��Ͻ��� ���� 1���� ���鵵�� ��ġ(=�̱��� ���� [GOF - Disign Pattern)

public class Dog {
	String name="�ǹ�";
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
