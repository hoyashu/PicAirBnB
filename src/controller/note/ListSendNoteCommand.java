package controller.note;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.NoteVo;
import model.service.NoteService;

public class ListSendNoteCommand implements Command {
	
	private static final int POST_PER_PAGE = 6;
	private static final int PAGE_BLOCK = 4;
	
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 유저 아이디 입력칸
		String userId = "airbnb1@java.com";
		// 1. 현재 페이지 번호를 구한다.
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage = 1;
		}
		
		// 2. 현재 페이지에 보여줄 DB에서 게시글의 시작 행 번호를 구한다.
		int startRow = (currentPage - 1) * POST_PER_PAGE;

		// 3. 보낸 쪽지 목록을 조회한다.
		NoteService noteService = NoteService.getInstance();
		ArrayList<NoteVo> sendNoteList = noteService.retrieveSendNoteList(userId, startRow, POST_PER_PAGE);
		request.setAttribute("sendNoteList", sendNoteList);

		// 4. BLOCK 을 구한다.
		int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1;

		// 5. 현재 페이지가 속한 페이지 블록의 시작 페이지 번호와 페이지 번호를 구한다.
		int startPage = 1 + (currentBlock - 1) * PAGE_BLOCK;
		int endPage = startPage + (PAGE_BLOCK - 1);

		// 5. 받은 쪽지 갯수를 구한다.
		int sendNoteCount = noteService.retrieveSendNoteCount(userId);
		request.setAttribute("sendNoteCount", sendNoteCount);

		// 6. 총 페이지 수를 구한다.
		int totalPage = sendNoteCount % POST_PER_PAGE == 0 ? sendNoteCount / POST_PER_PAGE
				: sendNoteCount / POST_PER_PAGE + 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}

		// 7. request 영역에 페이지 정보를 저장한다.
		request.setAttribute("pageBlock", PAGE_BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("sendNoteCount", sendNoteCount);
		request.setAttribute("postSize", POST_PER_PAGE);

		return new ActionForward("/Note_SendList.jsp?currentPage=" + currentPage, false);
	}

}
