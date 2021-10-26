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
			
			
			// 3.DB에서 게시글 번호에 해당하는 게시글 정보를 구한다.
			MemberService service = MemberService.getInstance();
			
			MemberVo member = service.loginMember(id, pwd);
			// 4.session 영역에 "member" 속성이름으로 게시글 정보를 저장한다.
			
			HttpSession session = req.getSession();
			
			if(member.getMemNo() == 0) {
				System.out.println(member.getMemNo());
				session.setAttribute("message", "아이디 혹은 비밀번호가 일치하지 않습니다.");
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
