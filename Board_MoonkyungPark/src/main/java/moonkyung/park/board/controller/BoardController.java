package moonkyung.park.board.controller;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import moonkyung.park.board.repository.BoardRepository;
import moonkyung.park.board.repository.CustomerRepository;
import moonkyung.park.board.repository.ReplyRepository;
import moonkyung.park.board.util.Pagination;
import moonkyung.park.board.vo.Board;
import moonkyung.park.board.vo.Reply;

@RequestMapping(value = "/boards")
@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	HttpSession session;

	@Inject
	BoardRepository bRepository;

	@Inject
	ReplyRepository rRepository;

	@Inject
	CustomerRepository cRepository;

	Pagination pagination = new Pagination();

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getBoards(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "searchType", defaultValue = "") String searchType,
			@RequestParam(value = "searchText", defaultValue = "") String searchText,
			@RequestParam(value = "orderBy", defaultValue = "") String orderBy) {
		logger.info("게시판 홈");
		logger.info("타입: " + searchType + "검색어: " + searchText);
		ArrayList<Board> boards = bRepository.getBoards(searchType, searchText, orderBy);
		int totalPages = pagination.totalPages(boards);
		page = pagination.getCurrentPage(page, totalPages);
		boards = pagination.totalPosts(boards, page);
		int endPage = pagination.endPage(page, totalPages);
		logger.info("총 페이지: " + totalPages + ", 끝페이지: " + endPage);
		model.addAttribute("boards", boards);
		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchText", searchText);
		model.addAttribute("orderBy", orderBy);
		return "boards/home";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertBoard() {
		logger.info("글 작성 화면으로 이동");
		return "boards/insert";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public @ResponseBody int insertBoard(Board board) {
		String board_id = (String) session.getAttribute("loginid");
		String board_nickname = cRepository.selectNickname(board_id);
		board.setBoard_id(board_id);
		board.setBoard_nickname(board_nickname);
		int result = bRepository.insertBoard(board);
		return result;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getBoard(int board_num, Model model) {
		String loginid = (String) session.getAttribute("loginid");
		Board board = bRepository.getBoard(board_num);
		if (!loginid.equals(board.getBoard_id())) {
			// 작성자는 자신이 작성한 글의 조회수를 올리지 못한다
			bRepository.upHits(board_num);
			board = bRepository.getBoard(board_num);
		}
		model.addAttribute("board", board);

		// 리플라이 부분
		ArrayList<Reply> reply = rRepository.getReplies(board_num);
		model.addAttribute("reply", reply);
		logger.info("글읽기");
		return "boards/get";
	}

	@RequestMapping(value = "/insertReply", method = RequestMethod.POST)
	public @ResponseBody int insertReply(int board_num, String reply_content,
			@RequestParam(value = "reply_num", defaultValue = "-1") int reply_num) {
		String reply_Id = (String) session.getAttribute("loginid");
		String reply_Nickname = (String) session.getAttribute("loginNickname");
		Reply newReply = new Reply();
		newReply.setBoard_num(board_num);
		newReply.setReply_content(reply_content);
		Reply reply = rRepository.getReply(reply_num);
		int result = 0;
		if (reply_num == -1) {
			// 원댓글인 경우
			newReply.setReply_id(reply_Id);
			newReply.setReply_nickname(reply_Nickname);
		} else {
			// 대댓인경우
			newReply.setReply_id(reply.getReply_id());
			newReply.setReply_nickname(reply.getReply_nickname());
			newReply.setRreply_id(reply_Id);
			newReply.setRreply_nickname(reply_Nickname);
			newReply.setRreply_num(reply_num);
		}
		result = rRepository.insertReply(newReply);
		// 원댓일 경우 rreply_num 추가로 넣어주기
		if (reply_num == -1) {
			reply_num = rRepository.recentlyAddedReplynum();
			logger.info("최근에 등록된 댓글 번호: " + reply_num);
			rRepository.updateRReply_num(reply_num);
		}
		// 0: 댓글 추가
		bRepository.changeReply(board_num, 0);
		logger.info("댓글 작성 결과: " + result);
		return board_num;
	}

	@RequestMapping(value = "/deleteReply", method = RequestMethod.POST)
	public @ResponseBody int deleteReply(int reply_num, int board_num) {
		int result = rRepository.deleteReply(reply_num);
		// 1: 댓글 삭제
		bRepository.changeReply(board_num, 1);
		logger.info("댓글 삭제 결과: " + result);
		return board_num;
	}

	@RequestMapping(value = "/updateReply", method = RequestMethod.POST)
	public @ResponseBody int updateReply(int reply_num, String reply_content, int board_num) {
		Reply reply = rRepository.getReply(reply_num);
		reply.setReply_content(reply_content);
		int result = rRepository.updateReply(reply);
		logger.info("댓글 수정");
		return board_num;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody int deleteBoard(int board_num) {
		int result = bRepository.deleteBoard(board_num);
		logger.info("글 삭제: " + result);
		return result;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateBoard(int board_num, Model model) {
		Board board = bRepository.getBoard(board_num);
		String loginid = (String) session.getAttribute("loginid");
		if (board.getBoard_id().equals(loginid)) {
			// 글쓴=로그인한사람 같을 때만 업뎃 가능하도록
			model.addAttribute("board", board);
			return "boards/update";
		}
		return "boards/home";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody int updateBoard(int board_num, String board_title, String board_content) {
		Board board = bRepository.getBoard(board_num);
		board.setBoard_title(board_title);
		board.setBoard_content(board_content);
		int result = bRepository.updateBoard(board);
		logger.info("글 수정 결과: " + result);
		return board_num;
	}
}