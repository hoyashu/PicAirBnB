package controller.board;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.RoomVo;
import model.service.RoomService;

public class WritePostFormCommand implements Command {
	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		////���� �ʿ��� �κ�
		//defaultList = ���� �ִ� �Խ����� �Խ��� vo ���� ��������
		//boardList = ��ü �Խ��� vo list ���� �������� 
		
		// �켱 ���Ƿ� 
		int defaultListNo = Integer.parseInt(req.getParameter("boardNo"));
		
		HashMap<Integer,String> boardList = new HashMap<Integer,String>();
		boardList.put(1,"���Ҹ���");
		boardList.put(2,"������õ");
		boardList.put(3,"���� ������");
		boardList.put(4,"�̺�Ʈ");
				
		// request ������ ����Ʈ �Խ��� ������ �����Ѵ�.
		req.setAttribute("defaultListNo", defaultListNo);
		// request ������ �Խ��� ����Ʈ ������ �����Ѵ�.
		req.setAttribute("boardList", boardList);
		
		
		// request ������ ���Ҹ���� �����Ѵ�. 
		ArrayList<RoomVo> roomList = RoomService.getInstance().retrieveRoomList();
		
		req.setAttribute("roomList", roomList);
		System.out.println(roomList);
		//�Խñ� ���� �� ��û ó�� Ŀ�ǵ�
		ActionForward forward = new ActionForward("/writePostForm.jsp", false);
		return forward;
		
	}
}
