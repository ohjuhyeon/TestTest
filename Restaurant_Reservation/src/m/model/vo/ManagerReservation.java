package m.model.vo;

import java.sql.Date;

public class ManagerReservation {
	private int resNo;
	private String cusName;
	private String cusPhone;
	private Date resDate;
	private int resTime;
	private int resCapacity;
	public int getResNo() {
		return resNo;
	}
	public void setResNo(int resNo) {
		this.resNo = resNo;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusPhone() {
		return cusPhone;
	}
	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}
	

	public Date getResDate() {
		return resDate;
	}
	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}
	public int getResTime() {
		return resTime;
	}
	public void setResTime(int resTime) {
		this.resTime = resTime;
	}
	
	public int getResCapacity() {
		return resCapacity;
	}
	public void setResCapacity(int resCapacity) {
		this.resCapacity = resCapacity;
	}
	
	@Override
	public String toString() {
		return cusName+"\t"+cusPhone+"\t"+resDate+"\t"+resTime+"시"+"\t"+resCapacity;
	}
	
	
}
