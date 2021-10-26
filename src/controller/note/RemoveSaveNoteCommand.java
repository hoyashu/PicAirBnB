package controller.note;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.NoteService;

public class RemoveSaveNoteCommand implements Command{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userId = "airbnb1@java.com";
		
		String[] arr = request.getParameterValues("noteNo");
		int[] noteNos = null;
		if (arr != null) {
			noteNos = new int[arr.length];
			for (int i=0; i< arr.length; i++) {
				noteNos[i] = Integer.parseInt(arr[i]);
			}
		} else {
			System.out.println("arrÀº null");
		}
		
		NoteService noteService = NoteService.getInstance();
		
		noteService.removeSaveNotes(userId, noteNos);
		
		return new ActionForward("/Note_SaveList.do", false);
	}
	
}
