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

			// 3.DB�뿉�꽌 寃뚯떆湲� 踰덊샇�뿉 �빐�떦�븯�뒗 寃뚯떆湲� �젙蹂대�� 援ы븳�떎.
			MemberService service = MemberService.getInstance();

			MemberVo member = service.loginMember(id, pwd);

			HttpSession session = req.getSession();

			if (member.getMemNo() == 0) { //�쉶�썝�씠 �븘�땶寃쎌슦
				req.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
				return new ActionForward("/member_login.jsp", false);
			} else {
				req.removeAttribute("message");
        // 4.session �쁺�뿭�뿉 "member" �냽�꽦�씠由꾩쑝濡� 寃뚯떆湲� �젙蹂대�� ���옣�븳�떎.
				session.setAttribute("member", member);

				return new ActionForward("/index.jsp", true);
			}
		} catch (Exception ex) {
			throw ex;
		}
	}
}