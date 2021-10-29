package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.PostVo;
import model.service.PostService;

public class CancelPostCommand implements Command {
	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//1. 삭제하고자 하는 게시글 번호를 구한다.
		int no = Integer.parseInt(req.getParameter("no"));
		//파일 삭제
		PostService postService = PostService.getInstance();
		System.out.println("no"+no);
		postService.removePost(no);
		
		HttpSession session = req.getSession();
		PostVo post = (PostVo) session.getAttribute("post");
		session.setAttribute("boardNo_MOD", post.getBoardNo());

		return new ActionForward("/listPost.do", true);
	}
}
