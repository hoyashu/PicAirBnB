package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.buf.StringUtils;

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
			String memNos = req.getParameter("memNos");
			String reason = req.getParameter("reason");
			System.out.println(memNos);
			
			String[] memNoList = memNos.split(",");
			System.out.println(memNoList);
	
			int[] numsA = new int[memNoList.length];
			for(int i=0;i<memNoList.length; i++){ numsA[i] = Integer.parseInt(memNoList[i]); }
				
			System.out.println(numsA); 

			MemberStateService service = MemberStateService.getInstance();
			service.registerMemberState(new MemberStateVo(numsA, reason));
			 
			service.reviseMemberState(new MemberStateVo(numsA, reason));
			
			
			
			return new ActionForward("/member_withdrawForm.jsp", true);
					
			} catch (Exception ex) {
				throw ex;
			}
	}
	
}
