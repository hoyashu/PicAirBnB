package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.MemberVo;
import model.service.MemberService;

public class ModifyMemberCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 회원 수정 요청을 처리할 커맨드
		//1. 사용자 가 입력한 회원 정보를 구한다.
		HttpSession session = req.getSession();
		
		MemberVo member = (MemberVo)session.getAttribute("member");
		int memNo = member.getMemNo();
		
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
		service.registerMember(new MemberVo(memNo, id, pwd, name, nick, gender, hp, birth));
		
		return new ActionForward("/modifyMemberForm.jsp", true);
				
		} catch (Exception ex) {
			throw ex;
		}
	}
	
}
