package model.service;

import java.util.ArrayList;
import java.util.List;

import domain.MemberVo;

import model.dao.member.MemberDao;

public class MemberService {

	private static MemberService service;

	private MemberService() {
	}

	public static MemberService getInstance() {
		if (service == null) {
			service = new MemberService();
		}
		return service;
	}

	// ȸ�� ���
	public void registerMember(MemberVo member) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		memberDao.insertMember(member);
	}

	// �α���
	public MemberVo loginMember(String id, String pwd) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.loginMember(id, pwd);
	}

	// ���̵� ã��
	public List<MemberVo> retrieveMemberId(String name, String birth) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectNBMemberList(name, birth);
	}

	// ȸ�� ��ȣ�� ������ ȸ����ȣ�� �����͸� ��ȸ�Ѵ�.
	public MemberVo retrieveMember(int memNo) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectMember(memNo);
	}

	// �Է��� �г����� �ߺ����� ��ȸ�Ѵ�.
	public int retrieveNickOverlapMember(String nick) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectNickOverlapMember(nick);
	}

	// �Է��� ���̵��� ȸ���� �����ϴ��� Ȯ���Ѵ�.
	public int retrieveMailOverlapMember(String id) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectEmailOverlapMember(id);
	}

	// ȸ�� ��ü��ȸ
	public ArrayList<MemberVo> retrieveMemberList(int startRow, int postSize) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectMemberList(startRow, postSize);
	}

	// ȸ�� �� ��ȸ
	public int retrieveTotalMemberCount() throws Exception {
		return MemberDao.getInstance().selectTotalMemberCount();
	}

	// ȸ�� ����
	public void reviseMember(MemberVo member) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		memberDao.updateMember(member);
	}

}
