package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.PostVo;
import model.service.PostService;


public class DetailPostCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//1. �Խñ� ��ȣ�� ���Ѵ�.
		int no = Integer.parseInt(req.getParameter("no"));
		
		PostService service = PostService.getInstance();
		PostVo post = service.retrievePost(no);

		post.setWriterName(req.getParameter("writerName"));
		
		req.setAttribute("post", post);
		return new ActionForward("/detailPost.jsp", false);
		
	}
}
	
