package controller.board;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.PostVo;
import model.service.PostService;

public class ModifyPostFormCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 게시글 수정 폼 요청 처리 커맨드
		// 1. 수정하고자 게시글 번호를 구한다.
		int no = Integer.parseInt(req.getParameter("no"));

		// 2. DB에서 게시글 번호에 해당하는 게시글 정보를 구한다.
		PostService postService = PostService.getInstance();
		PostVo post = postService.retrievePost(no);

		// 3. 세션 영역에 "board"라는 속성이름으로 게시글 정보를 저장한다.
		HttpSession session = req.getSession();
		session.setAttribute("post", post);

		//// 구현 필요한 부분
		// defaultList = 속해 있던 게시판의 게시판 vo 정보 가져오기
		// boardList = 전체 게시판 vo list 정보 가져오기

		// 우선 임의로
		HashMap<Integer, String> boardList = new HashMap<Integer, String>();
		boardList.put(1, "서울지역 숙소");
		boardList.put(2, "숙소여기어떄?");
		boardList.put(3, "호스트와 함께했어요");

		// request 영역에 게시판 리스트 정보를 저장한다.
		req.setAttribute("boardList", boardList);
		System.out.println("here");
		
		return new ActionForward("/modifyPostForm.jsp", false);

	}

}