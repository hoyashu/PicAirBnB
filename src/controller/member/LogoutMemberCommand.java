package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.MemberVo;
import domain.RealTime;

public class LogoutMemberCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			HttpSession session = req.getSession();
			if (session == null || session.getAttribute("member") == null
					|| session.getAttribute("member").equals("")) {
				return new ActionForward("/member_login.jsp", true);
			} else {
				// 6. RealTime Ŭ������ ����� ������ �߰��Ѵ�.
				
				MemberVo member = (MemberVo)session.getAttribute("member");
				String userId = member.getId();
				RealTime.memberIds.remove(userId);
				RealTime.memberCount = RealTime.memberIds.size();
				System.out.println("���� �Ϸ�");
				
				// ������ ���� �����͸� ��� ����
				session.invalidate();// ���� �� �ʱ�ȭ�ϱ�

				// �α׾ƿ��� �߱� ������ �������� �̵��ϵ��� �Ѵ�.
				return new ActionForward("/index.jsp", true); 
			}
		} catch (

		Exception ex) {
			throw ex;
		}
	}

}
