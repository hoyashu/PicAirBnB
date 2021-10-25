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

		//1. �����ϰ��� �ϴ�  ÷�������� ��ȣ�� ���Ѵ�.
		int no = Integer.parseInt(req.getParameter("attachNo"));

		//÷������ ����
		PostService postService = PostService.getInstance();
		postService.removePostAttach(no);
		
		
		//���� ������ ����� �Խñ� ������ ���Ѵ�.
		HttpSession session = req.getSession();
		PostVo post = (PostVo) session.getAttribute("post");
		
		return new ActionForward("/modifyPostForm.do?no="+post.getNo(), true);
	}
}
