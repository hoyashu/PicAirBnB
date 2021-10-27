package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.MemberService;

public class IdCheckMemberCommand implements Command{
	
	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String id = req.getParameter("id");

		MemberService service = MemberService.getInstance();
		
		int overLapId = service.retrieveMailOverlapMember(id);
		
		req.setAttribute("overLapId", overLapId);
				
		return new ActionForward("/views/overLapId.jsp", false);
	}
	
}
