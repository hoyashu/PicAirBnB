package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;
	private Map<String, String> map = new HashMap<String, String>();

	private CommandFactory() {
		
		// ȸ�� ��� �� ��û
		map.put("/writeMemberForm.do", "controller.member.WriteMemberFormCommand");
		// ȸ�� ��� ��û
		map.put("/writeMember.do", "controller.member.WriteMemberCommand");
		// �α��� �� ��û
		map.put("/loginMemberForm.do", "controller.member.LoginMemberFormCommand");
		// �α��� ��û
		map.put("/loginMember.do", "controller.member.LoginMemberCommand");
		// ȸ����� ��ȸ ��û
		map.put("/allMemberList.do","controller.member.AllMemberListCommand");
		// Ż��ȸ�� ��� ��ȸ ��û
		map.put("/withdrawMemberList.do","controller.member.WithdrawMemberListCommand");
		// ȸ�� ���� ��ȸ ��û
		map.put("/detailMember.do","controller.member.DetailMemberCommand");
		// ȸ�� ���� ���� ��û
		map.put("/modifyMember.do","controller.member.ModifyMemberCommand");
		// ȸ�� Ż�� �� ��û
		map.put("/withdrawMemberForm.do","controller.member.WithdrawMemberFormCommand");
		// ȸ�� Ż�� ��û
		map.put("/withdrawMember.do","controller.member.WithdrawMemberCommand");
		
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
