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

	// 회원 등록
	public void registerMember(MemberVo member) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		memberDao.insertMember(member);
	}

	// 로그인
	public MemberVo loginMember(String id, String pwd) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.loginMember(id, pwd);
	}

	// 아이디 찾기
	public List<MemberVo> retrieveMemberId(String name, String birth) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectNBMemberList(name, birth);
	}

	// 회원 번호로 지정된 회원번호의 데이터를 조회한다. 
	public MemberVo retrieveMember(int memNo) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectMember(memNo);
	}

	// 입력한 닉네임이 중복인지 조회한다.
	public boolean retrieveNickOverlapMember(String nick) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectNickOverlapMember(nick);
	}

	// 입력한 아이디의 회원이 존재하는지 확인한다.
	public boolean retrieveMailOverlapMember(String id) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectEmailOverlapMember(id);
	}

	// 회원 전체조회
	public ArrayList<MemberVo> retrieveMemberList(int startRow, int postSize) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectMemberList(startRow, postSize);
	}

	// 회원 수 조회
	public int retrieveTotalMemberCount() throws Exception {
		return MemberDao.getInstance().selectTotalMemberCount();
	}
  
	// 회원 수정
	public void reviseMember(MemberVo member) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		memberDao.updateMember(member);
	}
}
