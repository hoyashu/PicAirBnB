package controller.board;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.PostVo;
import model.service.PostService;


public class ListPostCommand implements Command {	
	
	private static final int POST_PER_PAGE = 5;
	private static final int PAGE_BLOCK = 3;
	
	
	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//1. 현재 페이지 번호를 구한다.
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(req.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage = 1;
		}
		
		//2. 현재 페이지에 보여줄 게시글의 시작 행 번호를 구한다.
		int startRow = (currentPage - 1) * POST_PER_PAGE;
		
		
		// 수정 필요 : 선택된 게시판에 따라, 해당 게시판에 소속되는 게시물 정보 불러오기
		//4. 해당하는 게시판 번호 저장하는 코드 구현 필요
		int boardNo = 1;
		try {
			boardNo = Integer.parseInt(req.getParameter("boardNo"));
		} catch (Exception e) {
			HttpSession session = req.getSession();
			boardNo =  Integer.parseInt(session.getAttribute("boardNo_MOD").toString());
			session.removeAttribute("boardNo_MOD");
		}
		
		//int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		
		System.out.println(boardNo);
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("boardNo", Integer.toString(boardNo));
		
		//3. DB에서 게시글 목록을 조회한다.
		ArrayList<PostVo> posts = PostService.getInstance().retrievePostSearchList(map, startRow, POST_PER_PAGE);
		
		//4. request 영역에 "boards" 속성이름으로 게시글 목록을 저장한다.
		req.setAttribute("posts", posts);
		
		//3. BLOCK 을 구한다.
		int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1;
		
		//4. 현재 페이지가 속한 페이지 블록의 시작 페이지 번호와 페이지 번호를 구한다.
		int startPage = 1 + (currentBlock - 1) * PAGE_BLOCK;
		int endPage = startPage + (PAGE_BLOCK - 1);
		
		//5. 총 게시글 수를 구한다.
		int totalPostCount = PostService.getInstance().retrievePostSearchListCount(map);	
		
		//6. 총 페이지 수를 구한다.
		int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE : 
														      totalPostCount / POST_PER_PAGE + 1 ;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		
		//7. request 영역에 페이지 정보를 저장한다.
		req.setAttribute("pageBlock", PAGE_BLOCK);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("totalPostCount", totalPostCount);
		req.setAttribute("postSize", POST_PER_PAGE);
		req.setAttribute("boardNo", boardNo);
		
		req.setAttribute("currentPage", currentPage);
		return new ActionForward("/listPost.jsp", false);

	}

}

















