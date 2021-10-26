package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;
	private Map<String, String> map = new HashMap<String, String>();

	private CommandFactory() {
		//아래 예시임
		// 게시글 쓰기 폼 요청
		map.put("/writeBoardForm.do", "controller.WriteBoardFormCommand");
		
		// 게시글 목록
		map.put("/listBoard.do", "controller.ListBoardCommand");
		
		// 게시글 읽기 요청
		map.put("/detailBoard.do", "controller.DetailBoardCommand");
		
		// 멤버 로그 리스트 요청
		map.put("/MemberLog_List.do", "controller.statistic.ListMemberLogCommand");
		
		// 멤버 로그 그래프 폼 요청
		map.put("/MemberLog_WeeklyGraphForm.do", "controller.statistic.ListMemberLogWeeklyGraphFormCommand");
		
		// 멤버 로그 그래프 요청
		map.put("/MemberLog_WeeklyGraph.do", "controller.statistic.ListMemberLogWeeklyGraphCommand");
		
		// 쪽지 쓰기 폼 요청
		map.put("/Note_WriteNoteForm.do", "controller.note.WriteNoteFormCommand");
		
		// 쪽지 쓰기 요청
		map.put("/Note_WriteNote.do", "controller.note.WriteNoteCommand");
		
		// 받은 쪽지 리스트 조회
		map.put("/Note_ReceiveList.do", "controller.note.ListReceiveNoteCommand");
		
		// 보낸 쪽지 리스트 조회
		map.put("/Note_SendList.do", "controller.note.ListSendNoteCommand");
		
		// 보관함 리스트 조회
		map.put("/Note_SaveList.do", "controller.note.ListSaveNoteCommand");
		
		// 쪽지 상세 조회
		map.put("/Note_DetailNote.do", "controller.note.DetailNoteCommand");
		
		// 선택한 쪽지 삭제
		map.put("/Note_CancelReceiveNote.do", "controller.note.RemoveReceiveNoteCommand");
		
		// 보관함 쪽지 삭제
		map.put("/Note_CancelSaveNote.do", "controller.note.RemoveSaveNoteCommand");
		
		// 보관함에 저장
		map.put("/Note_ModifyNoteSave.do","controller.note.ModifyNoteSaveCommand");
		
		// 보관함 쪽지 상세 조회
		map.put("/Note_DetailSaveNote.do", "controller.note.DetailSaveNoteCommand");
	}

	// 외부에서 중복된 생성자를 만드는 것을 방지하기 위해서 싱글톤 작성
	public static CommandFactory getInstance() {
		if (factory == null) {
			factory = new CommandFactory();
		}
		// CommandFactory()에서 만들어진 mep.put들이 전달됨
		return factory;
	}

	/* CommandFactory()의 map에 /writeBoard.do 애를 처리할 클래스가 있는지 확인한다. */
	public Command createCommand(String commandURI) throws Exception {
		String commandClass = map.get(commandURI);

		if (commandClass == null) {
			return null;
		}

		try {
			Class<?> cls = Class.forName(commandClass);

			// class 클래스의 생성자를 취득한다.
			Constructor<?> constructor = cls.getConstructor(null);
			System.out.println("constructor:" + constructor);
			
			// 생성자를 통해 newInstance 함수를 호출하여 Node 인스턴스를 생성한다
			Command command = (Command) constructor.newInstance();// 다형성
			return command;
			
		} catch (Exception e) {
			throw e;
		}
	}
}
