package controller.board;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.PostVo;
import model.service.PostService;


public class CancelPostAttachCommand implements Command {
	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//1. 삭제하고자 하는  첨부파일의 번호를 구한다.
		int no = Integer.parseInt(req.getParameter("attachNo"));

		//첨부파일 삭제
		PostService postService = PostService.getInstance();
		postService.removePostAttach(no);
		
		
		//세션 영역에 저장된 게시글 정보를 구한다.
		HttpSession session = req.getSession();
		PostVo post = (PostVo) session.getAttribute("post");
		
		return new ActionForward("/modifyPostForm.do?no="+post.getNo(), true);
	}
}
