package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.MemberVo;
import domain.RealTime;
import model.service.LogrecordService;
import model.service.MemberService;

public class LoginMemberCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			
			
			// 3.
			MemberService service = MemberService.getInstance();
			MemberVo member = service.loginMember(id, pwd);
			// 4.session 영역에 "member" 속성이름으로 게시글 정보를 저장한다.
			HttpSession session = req.getSession();
			
			if(member.getMemNo() == 0) {
				System.out.println(member.getMemNo());
				req.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
				return new ActionForward("/member_login.jsp", false);
			} else {
			session.removeAttribute("message");
			session.setAttribute("member", member);
			
			// 5. 로그 정보를 저장한다.
			String userIp = req.getRemoteAddr();
			String userId = member.getId();
			LogrecordService logrecordService = LogrecordService.getInstance();
			logrecordService.registerLogrecord(userIp, userId);
			
			// 6. RealTime 클래스에 사용자 정보를 추가한다.
			RealTime.memberIds.add(userId);
			RealTime.memberCount = RealTime.memberIds.size();
			System.out.println("추가 완료");
			
			System.out.println(member.getMemNo());
			
			return new ActionForward("/index.jsp", false);
			}
			
			} catch (Exception ex) {
				throw ex;
			}
	}

}
