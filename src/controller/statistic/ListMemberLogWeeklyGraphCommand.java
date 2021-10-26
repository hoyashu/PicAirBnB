package controller.statistic;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.LogrecordService;

public class ListMemberLogWeeklyGraphCommand implements Command{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int typeOfGraph = Integer.parseInt(request.getParameter("typeOfGraph"));
		int typeOfGraphNumber = 7;
		if(typeOfGraph == 2) {
			typeOfGraphNumber = 7;
		} else if(typeOfGraph == 3) {
			typeOfGraphNumber = 30;
		}
		
		LogrecordService service = LogrecordService.getInstance();
		ArrayList<Integer> typeOfGraphLecordCounts = service.retrieveTypeOfGraphLogrecordCount(typeOfGraphNumber);
		ArrayList<Integer> typeOfGraphPostCounts = service.retrieveTypeOfGraphPostCount(typeOfGraphNumber);
		request.setAttribute("typeOfGraphLecordCounts", typeOfGraphLecordCounts);
		request.setAttribute("typeOfGraphPostCounts", typeOfGraphPostCounts);
		request.setAttribute("typeOfGraph", typeOfGraph);
		
		return new ActionForward("/MemberLog_WeeklyGraphForm.jsp", false);
	}
	
}
