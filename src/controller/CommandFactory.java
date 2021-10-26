package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;
	private Map<String, String> map = new HashMap<String, String>();

	private CommandFactory() {

		// ��� �α� ����Ʈ ��û
		map.put("/MemberLog_List.do", "controller.statistic.ListMemberLogCommand");

		// ��� �α� �׷��� �� ��û
		map.put("/MemberLog_WeeklyGraphForm.do", "controller.statistic.ListMemberLogWeeklyGraphFormCommand");

		// ��� �α� �׷��� ��û
		map.put("/MemberLog_WeeklyGraph.do", "controller.statistic.ListMemberLogWeeklyGraphCommand");

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

		// ȸ�� ��� �� ��û
		map.put("/member_writeForm.do", "controller.member.WriteMemberFormCommand");
		// ȸ�� ��� ��û
		map.put("/member_write.do", "controller.member.WriteMemberCommand");
		// �α��� ��û
		map.put("/member_login.do", "controller.member.LoginMemberCommand");
		// ȸ����� ��ȸ ��û
		map.put("/member_allList.do", "controller.member.AllMemberListCommand");
		// Ż��ȸ�� ��� ��ȸ ��û
		map.put("/member_withdrawList.do", "controller.member.WithdrawMemberListCommand");
		// ȸ�� ���� ��ȸ ��û
		map.put("/detailMember.do", "controller.member.DetailMemberCommand");
		// ȸ�� ���� ���� ��û
		map.put("/mmember_modify.do", "controller.member.ModifyMemberCommand");
		// ȸ�� Ż�� �� ��û
		map.put("/member_withdrawForm.do", "controller.member.WithdrawMemberFormCommand");
		// ȸ�� Ż�� ��û
		map.put("/member_withdraw.do", "controller.member.WithdrawMemberCommand");
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
