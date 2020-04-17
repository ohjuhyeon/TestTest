package m.model.vo;

import m.controller.CustomerController;

public class Customer {
	private int Cus_No;
	private String Cus_Id;
	private String Cus_Name;
	private String Cus_Pwd;
	private String Cus_Phone;
	
	
	public Customer () {
	}


	public int getCus_No() {
		return Cus_No;
	}


	public void setCus_No(int cus_No) {
		Cus_No = cus_No;
	}


	public String getCus_Id() {
		return Cus_Id;
	}


	public void setCus_Id(String cus_Id) {
		Cus_Id = cus_Id;
	}


	public String getCus_Name() {
		return Cus_Name;
	}


	public void setCus_Name(String cus_Name) {
		Cus_Name = cus_Name;
	}


	public String getCus_Pwd() {
		return Cus_Pwd;
	}


	public void setCus_Pwd(String cus_Pwd) {
		Cus_Pwd = cus_Pwd;
	}


	public String getCus_Phone() {
		return Cus_Phone;
	}


	public void setCus_Phone(String cus_Phone) {
		Cus_Phone = cus_Phone;
	}
		
		

}
