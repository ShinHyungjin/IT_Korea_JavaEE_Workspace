package blood;

public class BloodManager {
	
	public String getAdvisor(String blood) {
		String msg = null;
		
		if(blood.equals("A")) {
			msg = "�Ĳ��ϰ� �����ϰ� ���ϰ� �����ϳ� ���� �ҽ��ϴ�";
		}else if(blood.equals("B")) {
			msg = "���ڴ� ������ ���, ���ڴ� �����ϳ� �ŷ��ְ� ���ϰ� Ȱ����";
		}else if(blood.equals("O")) {
			msg = "�米���̰� �ձ۵ձ������� �������� �д�";
		}else if(blood.equals("AB")) {
			msg = "������ ���� �ٲ�� �����´�. 4�������̴�";
		}
		return msg;
	} 	
}
