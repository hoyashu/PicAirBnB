package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.MemberVo;

public class WithdrawMemberFormCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		
		 String memNoList = req.getParameter("memNoList"); 
		 String[] memNo = memNoList.split(","); 
		 int[] memNos = new int[memNo.length]; 
		 for(int i=0;i<memNo.length; i++) { 
			 memNos[i] = Integer.parseInt(memNo[i]);
		 }
		 
		 String nickList = req.getParameter("nickList"); 
		 String[] nick =nickList.split(","); 
		 String[] nicks = new String[nick.length]; 
		 for(int i=0;i<nick.length; i++) { 
			 nicks[i] = nick[i];
		 }
		 
		 req.setAttribute("memNos", memNos);
		 req.setAttribute("nickList", nickList);
		 
		ActionForward foward = new ActionForward("/withdrawMemberForm.jsp", false);
		return foward;
	}

}
