package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;

import domain.MemberVo;

import model.service.MemberService;

public class DetailMemberCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
				
				HttpSession session = req.getSession();
				
				MemberVo member = (MemberVo)session.getAttribute("member");
				int memNo = member.getMemNo();
				
				MemberService service = MemberService.getInstance();
				member = service.retrieveMember(memNo);
				
				req.setAttribute("member", member);
				
				return new ActionForward("/detailMember.jsp", false);
	}
	
}
