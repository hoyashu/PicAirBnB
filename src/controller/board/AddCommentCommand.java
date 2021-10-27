package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.CommentVo;
import domain.MemberVo;
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
			//* 수정 필요 :  작성자 정보를 세션에 바인딩된 로그인 사용자의 id로 지정한다
			HttpSession session = req.getSession();
			MemberVo member = (MemberVo)session.getAttribute("member");
			int userId = member.getMemNo();
			
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
