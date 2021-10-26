package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;

import domain.MemberVo;

import model.service.MemberService;

public class LoginMemberCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			
			
			// 3.DB���� �Խñ� ��ȣ�� �ش��ϴ� �Խñ� ������ ���Ѵ�.
			MemberService service = MemberService.getInstance();
			
			MemberVo member = service.loginMember(id, pwd);
			// 4.session ������ "member" �Ӽ��̸����� �Խñ� ������ �����Ѵ�.
			
			HttpSession session = req.getSession();
			
			if(member.getMemNo() == 0) {
				System.out.println(member.getMemNo());
				session.setAttribute("message", "���̵� Ȥ�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				return new ActionForward("/member_login.jsp", false);
			} else {
			session.removeAttribute("message");
			session.setAttribute("member", member);
			
			System.out.println(member.getMemNo());
			
			return new ActionForward("/member_login.jsp", false);
			}
			
			} catch (Exception ex) {
				throw ex;
			}
	}

}
