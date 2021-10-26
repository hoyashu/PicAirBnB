package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;
	private Map<String, String> map = new HashMap<String, String>();

	private CommandFactory() {
		// 게시글 리스트 요청
		map.put("/listPost.do", "controller.board.ListPostCommand");
		
		// 게시글 작성 폼 요청
		map.put("/writePostForm.do", "controller.board.WritePostFormCommand");
		
		// 게시글 상세보기 요청 
		map.put("/detailPost.do", "controller.board.DetailPostCommand");
		
		// 게시글 수정 폼 요청 
		map.put("/modifyPostForm.do", "controller.board.ModifyPostFormCommand");
		
		// 첨부 파일 삭제 요청 
		map.put("/cancelPostAttach.do", "controller.board.CancelPostAttachCommand");
		
		// 게시글 삭제 요청 
		map.put("/cancelPost.do", "controller.board.CancelPostCommand");
		
		//댓글 등록		
		map.put("/addComment.do", "controller.board.AddCommentCommand");
		// 댓글 수정
		map.put("/modifyComment.do", "controller.board.ModifyCommentCommand");
		// 댓글 삭제
		map.put("/removeComment.do", "controller.board.RemoveCommentCommand");
		
		
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
