package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.CommentVo;
import model.dao.board.CommentDao;


public class AddCommentCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse resp)
		throws Exception  {
		/*
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		*/
		try {
			int userId = 1;
			String content = req.getParameter("cmtContent");
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			CommentDao commentDao = CommentDao.getInstance();
			commentDao.insertComment(new CommentVo(userId, content, boardNo));
			
			List<CommentVo> commentList = commentDao.selectCommentList(boardNo);
			req.setAttribute("commentList", commentList);
			return new ActionForward("/listComment.jsp", false);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}	
}
