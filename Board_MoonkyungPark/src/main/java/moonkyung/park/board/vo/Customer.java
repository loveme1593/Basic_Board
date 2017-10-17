package moonkyung.park.board.vo;

public class Customer {

	private String cus_id;
	private String cus_pw;
	private String cus_nickname;

	public String getCus_id() {
		return cus_id;
	}

	public void setCus_id(String cus_id) {
		this.cus_id = cus_id;
	}

	public String getCus_pw() {
		return cus_pw;
	}

	public void setCus_pw(String cus_pw) {
		this.cus_pw = cus_pw;
	}

	public String getCus_nickname() {
		return cus_nickname;
	}

	public void setCus_nickname(String cus_nickname) {
		this.cus_nickname = cus_nickname;
	}

	@Override
	public String toString() {
		return "Customer [cus_id=" + cus_id + ", cus_pw=" + cus_pw + ", cus_nickname=" + cus_nickname + "]";
	}

}
