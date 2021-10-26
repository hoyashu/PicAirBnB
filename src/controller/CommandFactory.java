package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;
	private Map<String, String> map = new HashMap<String, String>();

	private CommandFactory() {
     //******** 회원 ********//
		// 회원 등록 폼 요청
		map.put("/member_writeForm.do", "controller.member.WriteMemberFormCommand");
    
		// 회원 등록 요청
		map.put("/member_write.do", "controller.member.WriteMemberCommand");
    
		// 로그인 요청
		map.put("/member_login.do", "controller.member.LoginMemberCommand");
    
		// 로그아웃 요청
		map.put("/member_logout.do", "controller.member.LogoutMemberCommand");
    
		// 회원목록 조회 요청
		map.put("/member_allList.do", "controller.member.AllMemberListCommand");
    
		// 탈퇴회원 목록 조회 요청
		map.put("/member_withdrawList.do", "controller.member.WithdrawMemberListCommand");
    
		// 아이디 찾기
		map.put("/member_findId.do", "controller.member.FindIdMemberCommand");
    
		// 비밀번호 찾기 인증 메일 발송
		map.put("/member_sendCertifyCode.do", "controller.member.SendMemberMailCommand");
    
		// 회원 정보 수정 폼 요청
		map.put("/member_detailForm.do", "controller.member.ModifyMemberFormCommand");
    
		// 회원 정보 수정 요청
		map.put("/member_modify.do", "controller.member.ModifyMemberCommand");
    
		// 회원 탈퇴 폼 요청
		map.put("/member_withdrawForm.do", "controller.member.WithdrawMemberFormCommand");
    
		// 회원 탈퇴 요청
		map.put("/member_withdraw.do", "controller.member.WithdrawMemberCommand");
    
    //******** 통계 ********//
		// 멤버 로그 리스트 요청
		map.put("/MemberLog_List.do", "controller.statistic.ListMemberLogCommand");
		
		// 멤버 로그 그래프 폼 요청
		map.put("/MemberLog_WeeklyGraphForm.do", "controller.statistic.ListMemberLogWeeklyGraphFormCommand");
		
		// 멤버 로그 그래프 요청
		map.put("/MemberLog_WeeklyGraph.do", "controller.statistic.ListMemberLogWeeklyGraphCommand");
		
    //******** 쪽지 ********//
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

    //******** 이벤트 ********//
		// 이벤트 목록 조회
		map.put("/EventList.do", "controller.event.ListEventCommand");

    //********  ********//
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