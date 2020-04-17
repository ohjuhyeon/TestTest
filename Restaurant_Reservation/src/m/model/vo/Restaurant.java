package m.model.vo;

public class Restaurant {
	private int restNo;
	private String restName;
	private String address;
	private String restPhone;
	private String foodType;
	private int restCapacity;
	
	public Restaurant () {}

	public int getRestNo() {
		return restNo;
	}

	public void setRestNo(int restNo) {
		this.restNo = restNo;
	}

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRestPhone() {
		return restPhone;
	}

	public void setRestPhone(String restPhone) {
		this.restPhone = restPhone;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public int getRestCapacity() {
		return restCapacity;
	}

	public void setRestCapacity(int restCapacity) {
		this.restCapacity = restCapacity;
	}
	
	@Override
	public String toString() {
		return this.restNo + ", " + this.restName + ", " + this.address + ", " + this.restPhone + ", " + this.foodType + ", " + this.restCapacity;
	}//override
}
