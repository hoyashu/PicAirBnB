package controller.note;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class DetailSaveNoteCommand implements Command{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int noteNo = Integer.parseInt(request.getParameter("noteNo"));
		int noteGetmbNo = Integer.parseInt(request.getParameter("noteGetmbNo"));
		int noteSendmbNo = Integer.parseInt(request.getParameter("noteSendmbNo"));
		String noteCon = request.getParameter("noteCon");
		String noteDateTime = request.getParameter("noteDateTime");
		
		
		request.setAttribute("noteNo", noteNo);
		request.setAttribute("noteGetmbNo", noteGetmbNo);
		request.setAttribute("noteSendmbNo", noteSendmbNo);
		request.setAttribute("noteCon", noteCon);
		request.setAttribute("noteDateTime", noteDateTime);
		return new ActionForward("/Note_DetailSaveNote.jsp", false);
	}
	
}
