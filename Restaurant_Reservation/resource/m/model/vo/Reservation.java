package m.model.vo;

import java.util.Date;

public class Reservation {

	public Reservation() {

	}

	private int res_NO;
	private int time;
	private int res_Capacity;
	private int rest_No;
	private int cus_No;
	private Date date;

	public int getRes_NO() {
		return res_NO;
	}

	public void setRes_NO(int res_NO) {
		this.res_NO = res_NO;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getRes_Capacity() {
		return res_Capacity;
	}

	public void setRes_Capacity(int res_Capacity) {
		this.res_Capacity = res_Capacity;
	}

	public int getRest_No() {
		return rest_No;
	}

	public void setRest_No(int rest_No) {
		this.rest_No = rest_No;
	}

	public int getCus_No() {
		return cus_No;
	}

	public void setCus_No(int cus_No) {
		this.cus_No = cus_No;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		
		
		
		return "= = 예약 내역 확인 = =  예약 번호 : " + res_NO + ", 예약 시간 : " + time +" 시"+ ", 수용 가능 인원 : " + res_Capacity +
				" 명"+ ", 식당 번호 : " + rest_No+ ", 손님번호 : " + cus_No + ", 예약 날짜 : " + date ;
	}

}
