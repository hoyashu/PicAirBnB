package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class WriteMemberFormCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
				
			// ȸ�� ��� �� ��û ó�� Ŀ���
			ActionForward forward = new ActionForward("/writeMemberForm.jsp", false);
			return forward;
	}

	
}
