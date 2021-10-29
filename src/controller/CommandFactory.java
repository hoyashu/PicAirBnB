package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;
	private Map<String, String> map = new HashMap<String, String>();

	private CommandFactory() {
		// ******** ȸ�� ********//
		// ȸ�� ��� �� ��û
		map.put("/member_writeForm.do", "controller.member.WriteMemberFormCommand");

		// ȸ�� ��� ��û
		map.put("/member_write.do", "controller.member.WriteMemberCommand");

    // �ߺ� ���̵� üũ
		map.put("/member_idCheck.do", "controller.member.IdCheckMemberCommand");
		
		// �ߺ� �г��� üũ
		map.put("/member_nickCheck.do", "controller.member.NickCheckMemberCommand");
    
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

		// ******** �Խ��� ********//
		// �Խ��� ���� �� ��û
		map.put("/writeBoardForm.do", "controller.WriteBoardFormCommand");

		// �Խ��� ���������� ��û
		map.put("/listBoard.do", "controller.board.ListBoardCommand");

		// �Խ��� �б� ��û
		map.put("/detailBoard.do", "controller.DetailBoardCommand");

		// �Խ��� �׷� ���������� ��û
		map.put("/listBoardGroup.do", "controller.board.ListBoardGroupCommand");

		// ******** �Խñ� ********//
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

		// ��� ���
		map.put("/addComment.do", "controller.board.AddCommentCommand");

		// ��� ����
		map.put("/modifyComment.do", "controller.board.ModifyCommentCommand");

		// ��� ����
		map.put("/removeComment.do", "controller.board.RemoveCommentCommand");

		// ******** ��� ********//
		// ��� �α� ����Ʈ ��û
		map.put("/MemberLog_List.do", "controller.statistic.ListMemberLogCommand");

		// ��� �α� �׷��� �� ��û
		map.put("/MemberLog_WeeklyGraphForm.do", "controller.statistic.ListMemberLogWeeklyGraphFormCommand");

		// ��� �α� �׷��� ��û
		map.put("/MemberLog_WeeklyGraph.do", "controller.statistic.ListMemberLogWeeklyGraphCommand");

		// ******** ���� ********//
		// ���� ���� �� ��û
		map.put("/Note_WriteNoteForm.do", "controller.note.WriteNoteFormCommand");

		// ���� ���� ��û
		map.put("/Note_WriteNote.do", "controller.note.WriteNoteCommand");

		// ���� ���� ����Ʈ ��ȸ
		map.put("/Note_ReceiveList.do", "controller.note.ListReceiveNoteCommand");

		// ���� ���� ����Ʈ ��ȸ
		map.put("/Note_SendList.do", "controller.note.ListSendNoteCommand");

		// ������ ����Ʈ ��ȸ
		map.put("/Note_SaveList.do", "controller.note.ListSaveNoteCommand");

		// ���� �� ��ȸ
		map.put("/Note_DetailNote.do", "controller.note.DetailNoteCommand");

		// ������ ���� ����
		map.put("/Note_CancelReceiveNote.do", "controller.note.RemoveReceiveNoteCommand");

		// ������ ���� ����
		map.put("/Note_CancelSaveNote.do", "controller.note.RemoveSaveNoteCommand");

		// �����Կ� ����
		map.put("/Note_ModifyNoteSave.do", "controller.note.ModifyNoteSaveCommand");

		// ������ ���� �� ��ȸ
		map.put("/Note_DetailSaveNote.do", "controller.note.DetailSaveNoteCommand");

		// ******** �̺�Ʈ ********//
		// �̺�Ʈ ��� ��ȸ
		map.put("/EventList.do", "controller.event.ListEventCommand");

		// ******** �˸� ********//
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