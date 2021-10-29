package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

import domain.MemberVo;

import model.dao.member.MemberDao;
import model.service.MemberService;

public class WriteMemberCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// ȸ�� ��� ��û�� ó���� Ŀ�ǵ�
				//1. ����� �� �Է��� �Խñ� ������ ���Ѵ�.
				try {
				String id = req.getParameter("id");
				String nick = req.getParameter("nick");
				String name = req.getParameter("name");
				String pwd = req.getParameter("pwd");
				String gender = req.getParameter("gender");
				String hp = req.getParameter("hp");
				String birth = req.getParameter("birthDay");
				
				
				//2. ����ڰ� �Է��� ������ DB�� ����Ѵ�.
				MemberService service = MemberService.getInstance();
				service.registerMember(new MemberVo(id, pwd, name, nick, gender, hp, birth));
				
				return new ActionForward("/index.jsp", false);
						
				} catch (Exception ex) {
					throw ex;
				}
	}
	
}
