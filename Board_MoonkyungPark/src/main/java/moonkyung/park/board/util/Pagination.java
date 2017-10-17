package moonkyung.park.board.util;

import java.util.ArrayList;

import moonkyung.park.board.vo.Board;

public class Pagination {
	// 페이징처리 위한 클래스

	// 한 페이지당 나타나는 총 포스트 갯수
	private final int posts = 10;

	// 한 페이지당 나타나는 페이지의수
	private final int pages = 5;

	// 총 페이지 계산
	public int totalPages(ArrayList<Board> boards) {
		int result = 0;
		result = (boards.size() / posts);
		if (boards.size() % posts != 0 || result == 0) {
			result += 1;
		}
		return result;
	}

	// 마지막 페이지 구하기
	public int endPage(int page, int totalPages) {
		int endPage = 0;
		endPage = page + pages;
		if (endPage > totalPages) {
			endPage = totalPages;
		}
		return endPage;
	}

	// 한 페이지에 나타나는 게시글
	public ArrayList<Board> totalPosts(ArrayList<Board> boards, int page) {
		ArrayList<Board> result = new ArrayList();
		int startPost = (page - 1) * posts;
		int endPost = startPost + posts;
		if (endPost > boards.size()) {
			endPost = boards.size();
		}
		for (int a = startPost; a < endPost; a++) {
			result.add(boards.get(a));
		}
		return result;
	}

	// 페이지 수 조정
	public int getCurrentPage(int page, int totalPages) {
		if (page <= 0) {
			page = 1;
		} else if (page > totalPages && totalPages != 0) {
			page = totalPages;
		}
		return page;
	}

}
