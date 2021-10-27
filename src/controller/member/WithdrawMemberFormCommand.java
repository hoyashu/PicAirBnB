package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.MemberVo;

public class WithdrawMemberFormCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		
		 
		ActionForward foward = new ActionForward("/member_withdrawForm.jsp", false);
		return foward;
	}

}