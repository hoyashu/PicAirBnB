package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.MemberStateVo;
import domain.MemberVo;
import model.service.MemberService;
import model.service.MemberStateService;

public class WithdrawMemberCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			String[] memno = req.getParameterValues("memNo");
			
			
			
			//2. 사용자가 입력한 정보를 DB에 등록한다.
			/*
			 * MemberStateService service = MemberStateService.getInstance();
			 * service.registerMemberState(new MemberStateVo(memNo, reason));
			 */
			
			return new ActionForward("/member_withdrawForm.jsp", true);
					
			} catch (Exception ex) {
				throw ex;
			}
	}
	
}
