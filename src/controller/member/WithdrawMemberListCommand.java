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
		//1. ���� ������ ��ȣ�� ���Ѵ�.
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(req.getParameter("currentPage"));
			
			
		} catch (Exception e) {
			currentPage = 1;
		}
		
		//2. ���� �������� ������ �Խñ��� ���� �� ��ȣ�� ���Ѵ�.
		int startRow = (currentPage -1) * POST_PER_PAGE;
		
		//3. DB���� �Խñ� ����� ��ȸ�Ѵ�.
		ArrayList<MemberStateVo> memberstate = MemberStateService.getInstance().retrieveMemberStateList(startRow, POST_PER_PAGE);
		
		//4. request ������ "boards" �Ӽ��̸����� �Խñ� ����� �����Ѵ�.
		req.setAttribute("memberstate", memberstate);
		
		//5. BLOCK�� ���Ѵ�.
		int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1 ;
		
		//6. ���� �������� ���� ������ ����� ���� ������ ��ȣ�� ������ ��ȣ�� ���Ѵ�.
		int startPage = 1 + (currentBlock - 1) * PAGE_BLOCK;
		int endPage = startPage + (PAGE_BLOCK - 1);
		
		//7. �� �Խñ� ���� ���Ѵ�.
		int totalPostCount = MemberStateService.getInstance().retrieveTotalMemberStateCount();
		
		//7. �� ������ ���� ���Ѵ�.
		int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE : totalPostCount / POST_PER_PAGE + 1;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		// request ������ ������ ������ �����Ѵ�
		req.setAttribute("pageBlock", PAGE_BLOCK);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("totalPostCount", totalPostCount);
		req.setAttribute("postSize", POST_PER_PAGE);
		
		return new ActionForward("/member_withdrawList.jsp?currentPage=" + currentPage, false);
	}
}
