package controller.board;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.PostVo;
import model.service.PostService;

public class ModifyPostFormCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// �Խñ� ���� �� ��û ó�� Ŀ�ǵ�
		// 1. �����ϰ��� �Խñ� ��ȣ�� ���Ѵ�.
		int no = Integer.parseInt(req.getParameter("no"));

		// 2. DB���� �Խñ� ��ȣ�� �ش��ϴ� �Խñ� ������ ���Ѵ�.
		PostService postService = PostService.getInstance();
		PostVo post = postService.retrievePost(no);

		// 3. ���� ������ "board"��� �Ӽ��̸����� �Խñ� ������ �����Ѵ�.
		HttpSession session = req.getSession();
		session.setAttribute("post", post);

		//// ���� �ʿ��� �κ�
		// defaultList = ���� �ִ� �Խ����� �Խ��� vo ���� ��������
		// boardList = ��ü �Խ��� vo list ���� ��������

		// �켱 ���Ƿ�
		HashMap<Integer, String> boardList = new HashMap<Integer, String>();
		boardList.put(1, "�������� ����");
		boardList.put(2, "���ҿ���?");
		boardList.put(3, "ȣ��Ʈ�� �Բ��߾��");

		// request ������ �Խ��� ����Ʈ ������ �����Ѵ�.
		req.setAttribute("boardList", boardList);
		System.out.println("here");
		
		return new ActionForward("/modifyPostForm.jsp", false);

	}

}