package m.model.vo;

public class Notice {
	private int noticeNo;
	private String noticeContent;
	private int restNo;
	
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public int getRestNo() {
		return restNo;
	}
	public void setRestNo(int restNo) {
		this.restNo = restNo;
	}
	
	@Override
	public String toString() {
		return noticeContent;
	}
}
