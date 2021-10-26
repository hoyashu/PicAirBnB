package controller.note;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.NoteService;

public class ModifyNoteSaveCommand implements Command{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userId = "airbnb1@java.com";
		//아이디 넣는 곳
		int removePage = Integer.parseInt(request.getParameter("removePage"));
		System.out.println("removePage:"+removePage);
		String[] arr = request.getParameterValues("noteNo");
		int[] noteNos = null;
		if (arr != null) {
			noteNos = new int[arr.length];
			for (int i=0; i< arr.length; i++) {
				noteNos[i] = Integer.parseInt(arr[i]);
				System.out.println(noteNos[i]);
			}
		} else {
			System.out.println("arr은 null");
		}
		
		
		NoteService noteService = NoteService.getInstance();
		
		if(removePage == 1) { //수신 쪽지 보관함 저장
			noteService.reviseSaveRetrieveNote(userId, noteNos);
		} else if(removePage == 2) { //발신 쪽지 보관함 저장
			noteService.reviseSaveSendNote(userId, noteNos);
			
		}
		
		return new ActionForward("/Note_SaveList.do", false);
	}
	
}
