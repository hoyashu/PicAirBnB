package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.MemberService;

public class NickCheckMemberCommand implements Command{

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String nick = req.getParameter("nick");

		MemberService service = MemberService.getInstance();
		
		int overLapNick = service.retrieveNickOverlapMember(nick);
		
		req.setAttribute("overLapNick", overLapNick);
				
		return new ActionForward("/views/overLapNick.jsp", false);
	}

}
