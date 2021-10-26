package controller.note;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.NoteVo;
import model.service.NoteService;

public class ListSaveNoteCommand implements Command {

	private static final int POST_PER_PAGE = 6;
	private static final int PAGE_BLOCK = 4;

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// ���� ���̵� �Է�ĭ
		String userId = "airbnb1@java.com";
		// 1. ���� ������ ��ȣ�� ���Ѵ�.
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage = 1;
		}

		// 2. ���� �������� ������ DB���� �Խñ��� ���� �� ��ȣ�� ���Ѵ�.
		int startRow = (currentPage - 1) * POST_PER_PAGE;

		// 3. ���� ���� ����� ��ȸ�Ѵ�.
		NoteService noteService = NoteService.getInstance();
		ArrayList<NoteVo> saveNoteList = noteService.retrieveSaveNoteList(userId, startRow, POST_PER_PAGE);
		request.setAttribute("saveNoteList", saveNoteList);

		// 4. BLOCK �� ���Ѵ�.
		int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1;

		// 5. ���� �������� ���� ������ ������ ���� ������ ��ȣ�� ������ ��ȣ�� ���Ѵ�.
		int startPage = 1 + (currentBlock - 1) * PAGE_BLOCK;
		int endPage = startPage + (PAGE_BLOCK - 1);

		// 5. ���� ���� ������ ���Ѵ�.
		int retrieveSaveNoteCount = noteService.retrieveSaveNoteCount(userId);
		request.setAttribute("retrieveSaveNoteCount", retrieveSaveNoteCount);

		// 6. �� ������ ���� ���Ѵ�.
		int totalPage = retrieveSaveNoteCount % POST_PER_PAGE == 0 ? retrieveSaveNoteCount / POST_PER_PAGE
				: retrieveSaveNoteCount / POST_PER_PAGE + 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}

		// 7. request ������ ������ ������ �����Ѵ�.
		request.setAttribute("pageBlock", PAGE_BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("retrieveSaveNoteCount", retrieveSaveNoteCount);
		request.setAttribute("postSize", POST_PER_PAGE);

		return new ActionForward("/Note_SaveList.jsp?currentPage=" + currentPage, false);
	}

}