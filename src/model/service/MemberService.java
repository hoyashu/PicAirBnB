package model.service;


import java.util.ArrayList;


import domain.MemberVo;

import model.dao.member.MemberDao;


public class MemberService {
	
	private static MemberService service;
	
	private MemberService(){
	}
	
	public static MemberService getInstance() {
		if (service == null) {
			service = new MemberService();
		}
		return service;
	}
	
	public void registerMember(MemberVo member) throws Exception{
		MemberDao memberDao  = MemberDao.getInstance();
		memberDao.insertMember(member);
	}
	
	public MemberVo loginMember(String id, String pwd) throws Exception{
		MemberDao memberDao  = MemberDao.getInstance();
		return memberDao.loginMember(id, pwd);
	}
	
	public ArrayList<MemberVo> retrieveMemberList(int startRow, int postSize) throws Exception{
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectMemberList(startRow, postSize);
			
	}
	
	public int retrieveTotalMemberCount() throws Exception {
		return MemberDao.getInstance().selectTotalMemberCount();
	}
	
	// 회원 조회
	public void reviseMember(MemberVo member) throws Exception{
		MemberDao memberDao  = MemberDao.getInstance();
		memberDao.updateMember(member);
	}
	// 회원 수정
	public MemberVo retrieveMember(int memNo) throws Exception{
		MemberDao memberDao  = MemberDao.getInstance();
		return memberDao.selectMember(memNo);
	}
	
}
