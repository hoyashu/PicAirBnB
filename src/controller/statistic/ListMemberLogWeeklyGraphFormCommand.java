package controller.statistic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class ListMemberLogWeeklyGraphFormCommand implements Command{

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		return new ActionForward("/MemberLog_WeeklyGraphForm.jsp", false);
	}
	
	 
}
