package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;
	private Map<String, String> map = new HashMap<String, String>();

	private CommandFactory() {
		// �Խñ� ����Ʈ ��û
		map.put("/listPost.do", "controller.board.ListPostCommand");
		
		// �Խñ� �ۼ� �� ��û
		map.put("/writePostForm.do", "controller.board.WritePostFormCommand");
		
		// �Խñ� �󼼺��� ��û 
		map.put("/detailPost.do", "controller.board.DetailPostCommand");
		
		// �Խñ� ���� �� ��û 
		map.put("/modifyPostForm.do", "controller.board.ModifyPostFormCommand");
		
		// ÷�� ���� ���� ��û 
		map.put("/cancelPostAttach.do", "controller.board.CancelPostAttachCommand");
		
		// �Խñ� ���� ��û 
		map.put("/cancelPost.do", "controller.board.CancelPostCommand");
		
		//��� ���		
		map.put("/addComment.do", "controller.board.AddCommentCommand");
		// ��� ����
		map.put("/modifyComment.do", "controller.board.ModifyCommentCommand");
		// ��� ����
		map.put("/removeComment.do", "controller.board.RemoveCommentCommand");
		
		
	}

	// �ܺο��� �ߺ��� �����ڸ� ����� ���� �����ϱ� ���ؼ� �̱��� �ۼ�
	public static CommandFactory getInstance() {
		if (factory == null) {
			factory = new CommandFactory();
		}
		// CommandFactory()���� ������� mep.put���� ���޵�
		return factory;
	}

	/* CommandFactory()�� map�� /writeBoard.do �ָ� ó���� Ŭ������ �ִ��� Ȯ���Ѵ�. */
	public Command createCommand(String commandURI) throws Exception {
		String commandClass = map.get(commandURI);

		if (commandClass == null) {
			return null;
		}

		try {
			Class<?> cls = Class.forName(commandClass);

			// class Ŭ������ �����ڸ� ����Ѵ�.
			Constructor<?> constructor = cls.getConstructor(null);
			System.out.println("constructor:" + constructor);
			
			// �����ڸ� ���� newInstance �Լ��� ȣ���Ͽ� Node �ν��Ͻ��� �����Ѵ�
			Command command = (Command) constructor.newInstance();// ������
			return command;
			
		} catch (Exception e) {
			throw e;
		}
	}
}
