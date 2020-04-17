package m.model.vo;

import java.sql.Date;

public class ReservationTime {
	private Date resDate;
	private int restNo;
	private int time10;
	private int time12;
	private int time14;
	private int time16;
	private int time18;
	private int time20;
	
	public ReservationTime() {}

	public Date getResDate() {
		return resDate;
	}
	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}

	public int getRestNo() {
		return restNo;
	}
	public void setRestNo(int restNo) {
		this.restNo = restNo;
	}

	public int getTime10() {
		return time10;
	}
	public void setTime10(int time10) {
		this.time10 = time10;
	}

	public int getTime12() {
		return time12;
	}
	public void setTime12(int time12) {
		this.time12 = time12;
	}

	public int getTime14() {
		return time14;
	}
	public void setTime14(int time14) {
		this.time14 = time14;
	}

	public int getTime16() {
		return time16;
	}
	public void setTime16(int time16) {
		this.time16 = time16;
	}

	public int getTime18() {
		return time18;
	}
	public void setTime18(int time18) {
		this.time18 = time18;
	}

	public int getTime20() {
		return time20;
	}
	public void setTime20(int time20) {
		this.time20 = time20;
	}
	
	@Override
	public String toString() {
		return this.restNo + "\t" + this.resDate + "\t" + this.time10 + "\t" + this.time12 + "\t" + this.time14
				 + "\t" + this.time16 + "\t" + this.time18 + "\t" + this.time20;
	}
}
