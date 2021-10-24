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
	
	public MemberVo retrieveMember(String id, String pwd) throws Exception{
		MemberDao memberDao  = MemberDao.getInstance();
		return memberDao.selectMember(id, pwd);
	}
	
	public ArrayList<MemberVo> retrieveMemberList(int startRow, int postSize) throws Exception{
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectMemberList(startRow, postSize);
			
	}
	// ÃÑ ¸â¹ö ¼ö¸¦ ±¸ÇÏ´Ù
		public int retrieveTotalMemberCount() throws Exception {
			return MemberDao.getInstance().selectTotalMemberCount();
		}
	
	
}
