package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;
	private Map<String, String> map = new HashMap<String, String>();

	private CommandFactory() {
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
