package blood;

public class BloodManager {
	
	public String getAdvisor(String blood) {
		String msg = null;
		
		if(blood.equals("A")) {
			msg = "꼼꼼하고 세심하고 착하고 솔직하나 때론 소심하다";
		}else if(blood.equals("B")) {
			msg = "남자는 고집이 쎄고, 여자는 엉뚱하나 매력있고 쿨하고 활발함";
		}else if(blood.equals("O")) {
			msg = "사교적이고 둥글둥글하지만 오지랖이 넓다";
		}else if(blood.equals("AB")) {
			msg = "결정이 자주 바뀌고 뒤집는다. 4차원적이다";
		}
		return msg;
	} 	
}
