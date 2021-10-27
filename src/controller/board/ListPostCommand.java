package controller.board;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.PostVo;
import model.service.PostService;


public class ListPostCommand implements Command {	
	
	private static final int POST_PER_PAGE = 5;
	private static final int PAGE_BLOCK = 3;
	
	
	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//1. ���� ������ ��ȣ�� ���Ѵ�.
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(req.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage = 1;
		}
		
		//2. ���� �������� ������ �Խñ��� ���� �� ��ȣ�� ���Ѵ�.
		int startRow = (currentPage - 1) * POST_PER_PAGE;
		
		
		// ���� �ʿ� : ���õ� �Խ��ǿ� ����, �ش� �Խ��ǿ� �ҼӵǴ� �Խù� ���� �ҷ�����
		//4. �ش��ϴ� �Խ��� ��ȣ �����ϴ� �ڵ� ���� �ʿ�
		int boardNo = 1;
		try {
			boardNo = Integer.parseInt(req.getParameter("boardNo"));
		} catch (Exception e) {
			HttpSession session = req.getSession();
			boardNo =  Integer.parseInt(session.getAttribute("boardNo_MOD").toString());
			session.removeAttribute("boardNo_MOD");
		}
		
		//int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		
		System.out.println(boardNo);
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("boardNo", Integer.toString(boardNo));
		
		//3. DB���� �Խñ� ����� ��ȸ�Ѵ�.
		ArrayList<PostVo> posts = PostService.getInstance().retrievePostSearchList(map, startRow, POST_PER_PAGE);
		
		//4. request ������ "boards" �Ӽ��̸����� �Խñ� ����� �����Ѵ�.
		req.setAttribute("posts", posts);
		
		//3. BLOCK �� ���Ѵ�.
		int currentBlock = currentPage % PAGE_BLOCK == 0 ? currentPage / PAGE_BLOCK : currentPage / PAGE_BLOCK + 1;
		
		//4. ���� �������� ���� ������ ����� ���� ������ ��ȣ�� ������ ��ȣ�� ���Ѵ�.
		int startPage = 1 + (currentBlock - 1) * PAGE_BLOCK;
		int endPage = startPage + (PAGE_BLOCK - 1);
		
		//5. �� �Խñ� ���� ���Ѵ�.
		int totalPostCount = PostService.getInstance().retrievePostSearchListCount(map);	
		
		//6. �� ������ ���� ���Ѵ�.
		int totalPage = totalPostCount % POST_PER_PAGE == 0 ? totalPostCount / POST_PER_PAGE : 
														      totalPostCount / POST_PER_PAGE + 1 ;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		
		//7. request ������ ������ ������ �����Ѵ�.
		req.setAttribute("pageBlock", PAGE_BLOCK);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("totalPostCount", totalPostCount);
		req.setAttribute("postSize", POST_PER_PAGE);
		req.setAttribute("boardNo", boardNo);
		
		req.setAttribute("currentPage", currentPage);
		return new ActionForward("/listPost.jsp", false);

	}

}

















