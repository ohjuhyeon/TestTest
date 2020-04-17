package m.model.vo;

public class ReReview {
	private String cusId;
	private int rating;
	private String reContent;

	public ReReview() {

	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReContent() {
		return reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}

	@Override
	public String toString() {
		return this.cusId + "\t" + this.rating + "\t" + this.reContent;
	}
}
