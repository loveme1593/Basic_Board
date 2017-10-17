package moonkyung.park.board.dao;

import java.util.ArrayList;
import java.util.Map;

import moonkyung.park.board.vo.Board;

public interface BoardDAO {

	public int insertBoard(Board board) throws Exception;

	public ArrayList<Board> getBoards(Map<String, String> search) throws Exception;

	public Board getBoard(int board_num) throws Exception;

	public void upHits(int board_num) throws Exception;

	public int deleteBoard(int board_num) throws Exception;

	public int updateBoard(Board board) throws Exception;

	public void changeReply(Map<String, Integer> change) throws Exception;
}
