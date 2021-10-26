package controller.statistic;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.LogrecordVo;
import model.service.LogrecordService;

public class ListMemberLogCommand implements Command {

	private static final int POST_PER_PAGE = 6;
	private static final int PAGE_BLOCK = 4;

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 1. ���� ������ ��ȣ�� ���Ѵ�.
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage = 1;
		}

		// 2. ���� �������� ������ DB���� �Խñ��� ���� �� ��ȣ�� ���Ѵ�.
		int startRow = (currentPage - 1) * POST_PER_PAGE;

		// 3. �α� ����� ��ȸ�ϴ�.
		LogrecordService service = LogrecordService.getInstance();
		ArrayList<LogrecordVo> logrecords = service.retrieveMemberLogList(startRow, POST_PER_PAGE);
		request.setAttribute("logrecords", logrecords);

		// 3. BLOCK �� ���Ѵ�.
		int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1;

		// 4. ���� �������� ���� ������ ����� ���� ������ ��ȣ�� ������ ��ȣ�� ���Ѵ�.
		int startPage = 1 + (currentBlock - 1) * PAGE_BLOCK;
		int endPage = startPage + (PAGE_BLOCK - 1);

		// 5. �� �α� ���� ���Ѵ�.
		int logrecordCount = service.retrieveTotalLogrecordCount();
		request.setAttribute("logrecordCount", logrecordCount);

		// 6. �� ������ ���� ���Ѵ�.
		int totalPage = logrecordCount % POST_PER_PAGE == 0 ? logrecordCount / POST_PER_PAGE
				: logrecordCount / POST_PER_PAGE + 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}

		// 7. request ������ ������ ������ �����Ѵ�.
		request.setAttribute("pageBlock", PAGE_BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("logrecordCount", logrecordCount);
		request.setAttribute("postSize", POST_PER_PAGE);
		return new ActionForward("/MemberLog_List.jsp?currentPage=" + currentPage, false);
	}

}
