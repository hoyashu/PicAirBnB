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
		// 회원 등록 요청을 처리할 커맨드
				//1. 사용자 가 입력한 게시글 정보를 구한다.
				try {
				String id = req.getParameter("id");
				String nick = req.getParameter("nick");
				String name = req.getParameter("name");
				String pwd = req.getParameter("pwd");
				String gender = req.getParameter("gender");
				String hp = req.getParameter("hp");
				String birth = req.getParameter("birthDay");
				
				
				//2. 사용자가 입력한 정보를 DB에 등록한다.
				MemberService service = MemberService.getInstance();
				service.registerMember(new MemberVo(id, pwd, name, nick, gender, hp, birth));
				
				return new ActionForward("/index.jsp", false);
						
				} catch (Exception ex) {
					throw ex;
				}
	}
	
}
