package m.model.vo;

public class Restaurant {
	
	private int Rest_No;
	private String Rest_Name;
	private String Addr;
	private String Rest_Phone;
	private String Food_Type;
	private int Rest_Capa;
	
	public Restaurant() {
		
	}

	public int getRest_No() {
		return Rest_No;
	}

	public void setRest_No(int rest_No) {
		Rest_No = rest_No;
	}

	public String getRest_Name() {
		return Rest_Name;
	}

	public void setRest_Name(String rest_Name) {
		Rest_Name = rest_Name;
	}

	public String getAddr() {
		return Addr;
	}

	public void setAddr(String addr) {
		Addr = addr;
	}

	public String getRest_Phone() {
		return Rest_Phone;
	}

	public void setRest_Phone(String rest_Phone) {
		Rest_Phone = rest_Phone;
	}

	public String getFood_Type() {
		return Food_Type;
	}

	public void setFood_Type(String food_Type) {
		Food_Type = food_Type;
	}

	public int getRest_Capa() {
		return Rest_Capa;
	}

	public void setRest_Capa(int rest_Capa) {
		Rest_Capa = rest_Capa;
	}

	@Override
	public String toString() {
		return "= = 식당 = =  식당 번호 : " + Rest_No + ", 식당 이름 : " + Rest_Name + ", 주소 : " + Addr + ", 식당 전화 번호 : "
				+ Rest_Phone + ", 음식 분류 : " + Food_Type + ", 수용 가능 인원 : " + Rest_Capa + "";
	}
	
	

}//restaurant
