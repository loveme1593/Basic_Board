package moonkyung.park.board.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import moonkyung.park.board.dao.BoardDAO;
import moonkyung.park.board.dao.CustomerDAO;
import moonkyung.park.board.vo.Board;

@Repository
public class BoardRepository {

	@Inject
	private SqlSession sqlSession;

	BoardDAO bdao;

	public int insertBoard(Board board) {
		int result = 0;
		bdao = sqlSession.getMapper(BoardDAO.class);
		try {
			result = bdao.insertBoard(board);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Board> getBoards(String searchType, String searchText, String orderBy) {
		ArrayList<Board> result = new ArrayList();
		bdao = sqlSession.getMapper(BoardDAO.class);
		Map<String, String> search = new HashMap<>();
		search.put("searchType", searchType);
		search.put("searchText", searchText);
		search.put("orderBy", orderBy);
		try {
			result = bdao.getBoards(search);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Board getBoard(int board_num) {
		Board result = new Board();
		bdao = sqlSession.getMapper(BoardDAO.class);
		try {
			result = bdao.getBoard(board_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void upHits(int board_num) {
		// 조회수 올리기
		bdao = sqlSession.getMapper(BoardDAO.class);
		try {
			bdao.upHits(board_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int deleteBoard(int board_num) {
		int result = 0;
		bdao = sqlSession.getMapper(BoardDAO.class);
		try {
			result = bdao.deleteBoard(board_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updateBoard(Board board) {
		int result = 0;
		bdao = sqlSession.getMapper(BoardDAO.class);
		try {
			result = bdao.updateBoard(board);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public void changeReply(int board_num, int type) {
		bdao = sqlSession.getMapper(BoardDAO.class);
		Map<String, Integer> change = new HashMap();
		change.put("board_num", board_num);
		change.put("type", type);
		try {
			bdao.changeReply(change);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
