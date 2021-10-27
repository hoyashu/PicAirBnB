package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.CommentVo;
import model.dao.board.CommentDao;

public class RemoveCommentCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int no = Integer.parseInt(req.getParameter("no"));

		CommentDao commentDao = CommentDao.getInstance();
		
		commentDao.deleteComment(no);

		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		
		List<CommentVo> commentList = commentDao.selectCommentList(boardNo);
		
		for (CommentVo commentVo : commentList) {
			System.out.println(commentVo);
		}
		req.setAttribute("commentList", commentList);

		return new ActionForward("/listComment.jsp", false);
		
		
	}
}
