package moonkyung.park.board.vo;

public class Board {
	// 게시판

	// 게시글 고유 번호
	private int board_num;
	// 게시글 제목
	private String board_title;
	// 게시글 내용
	private String board_content;
	// 작성 날짜
	private String board_date;
	// 작성자 아이디
	private String board_id;
	// 조회수
	private int board_hits;
	// 닉네임
	private String board_nickname;
	// 댓글 수
	private int board_replies;

	public int getBoard_replies() {
		return board_replies;
	}

	public void setBoard_replies(int board_replies) {
		this.board_replies = board_replies;
	}

	public Board() {
		super();
	}

	public int getBoard_hits() {
		return board_hits;
	}

	public void setBoard_hits(int board_hits) {
		this.board_hits = board_hits;
	}

	public String getBoard_nickname() {
		return board_nickname;
	}

	public void setBoard_nickname(String board_nickname) {
		this.board_nickname = board_nickname;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getBoard_date() {
		return board_date;
	}

	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}

	public String getBoard_id() {
		return board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}

	@Override
	public String toString() {
		return "Board [board_num=" + board_num + ", board_title=" + board_title + ", board_content=" + board_content
				+ ", board_date=" + board_date + ", board_id=" + board_id + "]";
	}

}
