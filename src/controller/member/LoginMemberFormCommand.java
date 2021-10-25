package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class LoginMemberFormCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
			
		// 회원 등록 폼 요청 처리 커멘드
			ActionForward forward = new ActionForward("/loginMemberForm.jsp", false);
			return forward;
	}
	
}
