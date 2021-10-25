package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;
	private Map<String, String> map = new HashMap<String, String>();

	private CommandFactory() {
		// 이벤트 목록 조회
		map.put("/EventList.do", "controller.event.ListEventCommand");

		// 알림 발송
		map.put("/SendAlarm.do", "controller.alarm.WriteAlarmCommand");

		// 회원별 읽지 않은 알림 개수 조회
		map.put("/AlarmNoReadCount.do", "controller.alarm.CountNoReadAlarmCommand");

		// 회원별 알림 목록 조회
		map.put("/AlarmList.do", "controller.alarm.ListAlarmCommand");

		// 회원별 알림 선택 삭제
		map.put("/AlarmDelete.do", "controller.alarm.CancelAlarmCommand");

		// 회원별 알림 전체 삭제
		map.put("/AlarmAllDelete.do", "controller.alarm.CancelAllAlarmCommand");

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
