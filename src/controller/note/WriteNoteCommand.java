package controller.note;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import controller.ActionForward;
import controller.Command;
import model.service.NoteService;

public class WriteNoteCommand implements Command{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("utf-8");
		
//		String userId = (String)session.getAttribute("userId");
		String userId = "airbnb1@java.com";		
		String getMbIds = request.getParameter("getMbIds");
		String noteCon = request.getParameter("noteCon");
		
		//앞,뒤,중간 공백제거
		getMbIds = getMbIds.replaceAll(" " , "");
		getMbIds = getMbIds.replaceAll("\\p{Z}", "");
		String[] getMbIdArray = getMbIds.split(",");
		
		for (String string : getMbIdArray) {
			System.out.println(string);
		}
		
		NoteService service = NoteService.getInstance();
		service.registerNote(userId, noteCon, getMbIdArray);
		return new ActionForward("/Note_SendList.do", true);
	}
	
}
