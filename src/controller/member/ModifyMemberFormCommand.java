package controller.member;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.MemberVo;
import model.service.MemberService;

public class ModifyMemberFormCommand implements Command {
	// 수정버튼을 클릭했을 때 실행 됨

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();

		MemberVo member = (MemberVo) session.getAttribute("member");
		int memNo = member.getMemNo();
		try {
			MemberService service = MemberService.getInstance();
			MemberVo memberModidy = service.retrieveMember(memNo);
			session.setAttribute("memberModidy", memberModidy);
			
			return new ActionForward("/member_modify.jsp", false);

		} catch (Exception e) {
			req.setAttribute("exception", e);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
			dispatcher.forward(req, res);
			return null;
		}
	}

}
