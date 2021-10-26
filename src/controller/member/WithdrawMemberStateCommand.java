package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.MemberStateVo;
import model.service.MemberStateService;

public class WithdrawMemberStateCommand implements Command{

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			String memNos = req.getParameter("memNo_arr");
			
			System.out.println(memNos);
			
			String[] memNoList = memNos.split(",");
			System.out.println(memNoList);
	
			int[] numsA = new int[memNoList.length];
			for(int i=0;i<memNoList.length; i++){ numsA[i] = Integer.parseInt(memNoList[i]); }
				
			System.out.println(numsA); 

			/*
			 * MemberStateService service = MemberStateService.getInstance();
			 * service.registerMemberState(new MemberStateVo(numsA, reason));
			 */
			
			return new ActionForward("/member_withdrawList.jsp", true);
					
			} catch (Exception ex) {
				throw ex;
			}
	}
	
	
}
