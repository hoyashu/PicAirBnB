package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;
	private Map<String, String> map = new HashMap<String, String>();

	private CommandFactory() {

		// ȸ�� ��� �� ��û
		map.put("/member_writeForm.do", "controller.member.WriteMemberFormCommand");
		// ȸ�� ��� ��û
		map.put("/member_write.do", "controller.member.WriteMemberCommand");
		// �α��� ��û
		map.put("/member_login.do", "controller.member.LoginMemberCommand");
		// �α׾ƿ� ��û
		map.put("/member_logout.do", "controller.member.LogoutMemberCommand");
		// ȸ����� ��ȸ ��û
		map.put("/member_allList.do", "controller.member.AllMemberListCommand");
		// Ż��ȸ�� ��� ��ȸ ��û
		map.put("/member_withdrawList.do", "controller.member.WithdrawMemberListCommand");
		// ���̵� ã��
		map.put("/member_findId.do", "controller.member.FindIdMemberCommand");
		// ��й�ȣ ã�� ���� ���� �߼�
		map.put("/member_sendCertifyCode.do", "controller.member.SendMemberMailCommand");
		// ȸ�� ���� ���� �� ��û
		map.put("/member_detailForm.do", "controller.member.ModifyMemberFormCommand");
		// ȸ�� ���� ���� ��û
		map.put("/member_modify.do", "controller.member.ModifyMemberCommand");
		// ȸ�� Ż�� �� ��û
		map.put("/member_withdrawForm.do", "controller.member.WithdrawMemberFormCommand");
		// ȸ�� Ż�� ��û
		map.put("/member_withdraw.do", "controller.member.WithdrawMemberCommand");

		// �̺�Ʈ ��� ��ȸ
		map.put("/EventList.do", "controller.event.ListEventCommand");

		// �˸� �߼�
		map.put("/SendAlarm.do", "controller.alarm.WriteAlarmCommand");

		// ȸ���� ���� ���� �˸� ���� ��ȸ
		map.put("/AlarmNoReadCount.do", "controller.alarm.CountNoReadAlarmCommand");

		// ȸ���� �˸� ��� ��ȸ
		map.put("/AlarmList.do", "controller.alarm.ListAlarmCommand");

		// ȸ���� �˸� ���� ����
		map.put("/AlarmDelete.do", "controller.alarm.CancelAlarmCommand");

		// ȸ���� �˸� ��ü ����
		map.put("/AlarmAllDelete.do", "controller.alarm.CancelAllAlarmCommand");
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
