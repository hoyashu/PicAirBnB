package controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.MemberVo;
import model.service.MemberService;

public class FindIdMemberCommand  implements Command{
	
	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String name = req.getParameter("name");
		String birth = req.getParameter("birth");

		MemberService service = MemberService.getInstance();
		
		List<MemberVo> findIdResultList = service.retrieveMemberId(name, birth);
		
		req.setAttribute("findIdResultList", findIdResultList);
				
		return new ActionForward("/member_findIdResult.jsp", false);
	}
	
}
