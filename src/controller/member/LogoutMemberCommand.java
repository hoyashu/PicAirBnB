package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;

public class LogoutMemberCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			HttpSession session = req.getSession();
			if (session == null || session.getAttribute("member") == null
					|| session.getAttribute("member").equals("")) {
				return new ActionForward("/member_login.jsp", true);
			} else {
				// 기존의 세션 데이터를 모두 삭제
				session.invalidate();// 세션 값 초기화하기

				// 로그아웃을 했기 때문에 메인으로 이동하도록 한다.
				return new ActionForward("/index.jsp", true); 
			}
		} catch (

		Exception ex) {
			throw ex;
		}
	}

}
