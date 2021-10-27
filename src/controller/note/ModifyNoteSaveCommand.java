package controller.note;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.MemberVo;
import model.service.NoteService;

public class ModifyNoteSaveCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// ���� ���̵� �Է�ĭ
		HttpSession session = request.getSession();
		MemberVo member = (MemberVo) session.getAttribute("member");
		String userId = member.getId();
		
		int removePage = Integer.parseInt(request.getParameter("removePage"));
		System.out.println("removePage:" + removePage);
		String[] arr = request.getParameterValues("noteNo");
		int[] noteNos = null;
		if (arr != null) {
			noteNos = new int[arr.length];
			for (int i = 0; i < arr.length; i++) {
				noteNos[i] = Integer.parseInt(arr[i]);
				System.out.println(noteNos[i]);
			}
		} else {
			System.out.println("arr�� null");
		}

		NoteService noteService = NoteService.getInstance();

		if (removePage == 1) { // ���� ���� ������ ����
			noteService.reviseSaveRetrieveNote(userId, noteNos);
		} else if (removePage == 2) { // �߽� ���� ������ ����
			noteService.reviseSaveSendNote(userId, noteNos);

		}

		return new ActionForward("/Note_SaveList.do", false);
	}

}
