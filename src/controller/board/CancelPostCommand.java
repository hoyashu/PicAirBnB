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
		
		//1. �����ϰ��� �ϴ� �Խñ� ��ȣ�� ���Ѵ�.
		int no = Integer.parseInt(req.getParameter("no"));
		//���� ����
		PostService postService = PostService.getInstance();
		postService.removePost(no);

		return new ActionForward("/listPost.do", true);
	}
}