package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.CommentVo;
import domain.PostVo;
import model.dao.board.CommentDao;
import model.service.PostService;


public class DetailPostCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//1. 게시글 번호를 구한다.
		int no = Integer.parseInt(req.getParameter("no"));
		
		PostService service = PostService.getInstance();
		PostVo post = service.retrievePost(no);

		post.setWriterName(req.getParameter("writerName"));
		
		req.setAttribute("post", post);
		
		List<CommentVo> commentList = CommentDao.getInstance().selectCommentList(no);
		
		req.setAttribute("commentList", commentList);
		return new ActionForward("/detailPost.jsp", false);
		
	}
}
	
