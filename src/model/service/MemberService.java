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

	// �쉶�썝 �벑濡�
	public void registerMember(MemberVo member) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		memberDao.insertMember(member);
	}

	// 濡쒓렇�씤
	public MemberVo loginMember(String id, String pwd) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.loginMember(id, pwd);
	}

	// �븘�씠�뵒 李얘린
	public List<MemberVo> retrieveMemberId(String name, String birth) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectNBMemberList(name, birth);
	}

	// �쉶�썝 踰덊샇濡� 吏��젙�맂 �쉶�썝踰덊샇�쓽 �뜲�씠�꽣瑜� 議고쉶�븳�떎. 
	public MemberVo retrieveMember(int memNo) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectMember(memNo);
	}

	// �엯�젰�븳 �땳�꽕�엫�씠 以묐났�씤吏� 議고쉶�븳�떎.
	public int retrieveNickOverlapMember(String nick) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectNickOverlapMember(nick);
	}

	// �엯�젰�븳 �븘�씠�뵒�쓽 �쉶�썝�씠 議댁옱�븯�뒗吏� �솗�씤�븳�떎.
	public int retrieveMailOverlapMember(String id) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectEmailOverlapMember(id);
	}

	// �쉶�썝 �쟾泥댁“�쉶
	public ArrayList<MemberVo> retrieveMemberList(int startRow, int postSize) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		return memberDao.selectMemberList(startRow, postSize);
	}

	// �쉶�썝 �닔 議고쉶
	public int retrieveTotalMemberCount() throws Exception {
		return MemberDao.getInstance().selectTotalMemberCount();
	}
  
	// �쉶�썝 �닔�젙
	public void reviseMember(MemberVo member) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();
		memberDao.updateMember(member);
	}

}
