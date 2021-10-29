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
		
		////구현 필요한 부분
		//defaultList = 속해 있던 게시판의 게시판 vo 정보 가져오기
		//boardList = 전체 게시판 vo list 정보 가져오기 
		
		// 우선 임의로 
		int defaultListNo = Integer.parseInt(req.getParameter("boardNo"));
		
		HashMap<Integer,String> boardList = new HashMap<Integer,String>();
		boardList.put(1,"숙소리뷰");
		boardList.put(2,"숙소추천");
		boardList.put(3,"숙소 사진전");
		boardList.put(4,"이벤트");
				
		// request 영역에 디폴트 게시판 정보를 저장한다.
		req.setAttribute("defaultListNo", defaultListNo);
		// request 영역에 게시판 리스트 정보를 저장한다.
		req.setAttribute("boardList", boardList);
		
		
		// request 영역에 숙소목록을 저장한다. 
		ArrayList<RoomVo> roomList = RoomService.getInstance().retrieveRoomList();
		
		req.setAttribute("roomList", roomList);
		System.out.println(roomList);
		//게시글 쓰기 폼 요청 처리 커맨드
		ActionForward forward = new ActionForward("/writePostForm.jsp", false);
		return forward;
		
	}
}
