package controller.board;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class WritePostFormCommand implements Command {
	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		////���� �ʿ��� �κ�
		//defaultList = ���� �ִ� �Խ����� �Խ��� vo ���� ��������
		//boardList = ��ü �Խ��� vo list ���� �������� 
		
		// �켱 ���Ƿ� 
		int defaultListNo = 2;
		HashMap<Integer,String> boardList = new HashMap<Integer,String>();
		boardList.put(1,"�������� ����");
		boardList.put(2,"���ҿ���?");
		boardList.put(3,"ȣ��Ʈ�� �Բ��߾��");
				
		// request ������ ����Ʈ �Խ��� ������ �����Ѵ�.
		req.setAttribute("defaultListNo", defaultListNo);
		// request ������ �Խ��� ����Ʈ ������ �����Ѵ�.
		req.setAttribute("boardList", boardList);
		
		
		//�Խñ� ���� �� ��û ó�� Ŀ�ǵ�
		ActionForward forward = new ActionForward("/writePostForm.jsp", false);
		return forward;
		
	}
}
