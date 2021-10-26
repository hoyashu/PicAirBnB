package controller.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.MemberStateVo;
import domain.MemberVo;
import model.service.MemberService;
import model.service.MemberStateService;

public class WithdrawMemberListCommand implements Command{
	private static final int POST_PER_PAGE = 10;
	private static final int PAGE_BLOCK = 5;
	
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
		int startRow = (currentPage -1) * POST_PER_PAGE;
		
		//3. DB에서 게시글 목록을 조회한다.
		ArrayList<MemberStateVo> memberstate = MemberStateService.getInstance().retrieveMemberStateList(startRow, POST_PER_PAGE);
		
		//4. request 영역에 "boards" 속성이름으로 게시글 목록을 저장한다.
		req.setAttribute("memberstate", memberstate);
		
		//5. BLOCK을 구한다.
		int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1 ;
		
		//6. 현재 페이지가 속한 페이지 블록의 시작 페이지 번호와 페이지 번호를 구한다.
		int startPage = 1 + (currentBlock - 1) * PAGE_BLOCK;
		int endPage = startPage + (PAGE_BLOCK - 1);
		
		//7. 총 게시글 수를 구한다.
		int totalPostCount = MemberStateService.getInstance().retrieveTotalMemberStateCount();
		
		//7. 총 페이지 수를 구한다.
		int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE : totalPostCount / POST_PER_PAGE + 1;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		// request 영역에 페이지 정보를 저장한다
		req.setAttribute("pageBlock", PAGE_BLOCK);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("totalPostCount", totalPostCount);
		req.setAttribute("postSize", POST_PER_PAGE);
		
		return new ActionForward("/member_withdrawList.jsp?currentPage=" + currentPage, false);
	}
}
