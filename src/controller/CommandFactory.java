package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;
	private Map<String, String> map = new HashMap<String, String>();

	private CommandFactory() {
		
		// 회원 등록 폼 요청
		map.put("/writeMemberForm.do", "controller.member.WriteMemberFormCommand");
		// 회원 등록 요청
		map.put("/writeMember.do", "controller.member.WriteMemberCommand");
		// 로그인 폼 요청
		map.put("/loginMemberForm.do", "controller.member.LoginMemberFormCommand");
		// 로그인 요청
		map.put("/loginMember.do", "controller.member.LoginMemberCommand");
		// 회원목록 조회 요청
		map.put("/allMemberList.do","controller.member.AllMemberListCommand");
		// 탈퇴회원 목록 조회 요청
		map.put("/withdrawMemberList.do","controller.member.WithdrawMemberListCommand");
		// 회원 정보 조회 요청
		map.put("/detailMember.do","controller.member.DetailMemberCommand");
		// 회원 정보 수정 요청
		map.put("/modifyMember.do","controller.member.ModifyMemberCommand");
		// 회원 탈퇴 폼 요청
		map.put("/withdrawMemberForm.do","controller.member.WithdrawMemberFormCommand");
		// 회원 탈퇴 요청
		map.put("/withdrawMember.do","controller.member.WithdrawMemberCommand");
		
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
