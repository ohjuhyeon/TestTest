package m.model.vo;

public class Customer {
	private int cusNo;
	private String cusId;
	private String cusName;
	private String cusPwd;
	private String cusPhone;
	
	public Customer () {}

	public int getCusNo() {
		return cusNo;
	}

	public void setCusNo(int cusNo) {
		this.cusNo = cusNo;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusPwd() {
		return cusPwd;
	}

	public void setCusPwd(String cusPwd) {
		this.cusPwd = cusPwd;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}
	
	@Override
	public String toString() {
		
		return this.cusNo + ", " + this.cusId + ", " + this.cusPwd + ", " + this.cusName + ", " + this.cusPhone;
		
	}
}
