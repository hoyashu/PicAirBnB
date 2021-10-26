package controller.note;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.MemberVo;
import model.service.NoteService;

public class RemoveSaveNoteCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 유저 아이디 입력칸
		HttpSession session = request.getSession();
		MemberVo member = (MemberVo) session.getAttribute("member");
		String userId = member.getId();

		String[] arr = request.getParameterValues("noteNo");
		int[] noteNos = null;
		if (arr != null) {
			noteNos = new int[arr.length];
			for (int i = 0; i < arr.length; i++) {
				noteNos[i] = Integer.parseInt(arr[i]);
			}
		} else {
			System.out.println("arr은 null");
		}

		NoteService noteService = NoteService.getInstance();

		noteService.removeSaveNotes(userId, noteNos);

		return new ActionForward("/Note_SaveList.do", false);
	}

}
