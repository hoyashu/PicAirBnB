package model.dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import domain.MemberVo;
import util.DBConn;


public class MemberDao {
	// singleton pattern
		private static MemberDao MemberDao;
		
		private MemberDao() {
			
		}
		
		public static MemberDao getInstance() {
			if (MemberDao == null) {
				MemberDao = new MemberDao();
			}
			return MemberDao;
		}
		
		// 회원 등록
		public void insertMember(MemberVo member) throws Exception {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			Statement stmt = null;
			ResultSet rs = null;

			try {
				conn = DBConn.getConnection();
				
				StringBuffer sql = new StringBuffer();
				sql.append("INSERT INTO member (mb_id, mb_nick, mb_name, mb_pwd, mb_gender, mb_hp, mb_birth, mb_datetime) ");
				sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, DATE_FORMAT(now(), '%Y/%m/%d %H:%i:%s'))");
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getNick());
				pstmt.setString(3, member.getName());
				pstmt.setString(4, member.getPwd());
				pstmt.setString(5, member.getGender());
				pstmt.setString(6, member.getHp());
				pstmt.setString(7, member.getBirth());
				
				pstmt.executeUpdate();
				
				
			} catch (Exception e) {
				throw e;
			} finally {
				try {
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close(); 
				} catch (Exception e2) {
					throw e2;
				}
			}
		}
		
		// 회원 조회
		public MemberVo selectMember(String id, String pwd) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MemberVo member = new MemberVo();	
			try {
				conn = DBConn.getConnection();
				
				StringBuffer sql = new StringBuffer();
				sql.append("select mb_no, mb_id, mb_nick from member "); 
				sql.append("where mb_id = ? and mb_pwd = ? ");
		
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1, id);
				pstmt.setString(2, pwd);
				
				rs = pstmt.executeQuery();
				
				
				while(rs.next()) {
						
						member.setMemNo(rs.getInt(1));
						member.setId(rs.getString(2));
						member.setNick(rs.getString(3));
						
				}
				
			} catch (Exception e) {
				throw e;
			} finally {
				try {
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				} catch (Exception e2) {
					throw e2;
				}
			}		
			return member;
		}
		
		// 게시글 목록을 조회한다
		public ArrayList<MemberVo> selectMemberList(int startRow, int postSize) throws Exception {
			ArrayList<MemberVo> boards = new ArrayList<MemberVo>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DBConn.getConnection();

				StringBuffer sql = new StringBuffer();
				sql.append("SELECT mb_no ,mb_nick, mb_grade, mb_datetime, mb_visit, mb_board, mb_comment, mb_gender   ");
				sql.append("FROM member  ");
				sql.append("ORDER BY mb_grade DESC LIMIT ? OFFSET ?; ");
	
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setInt(1, postSize);
				pstmt.setInt(2, startRow);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {
					int memNo = rs.getInt(1);
					String nick = rs.getString(2);
					int grade = rs.getInt(3);
					String joinDate = rs.getString(4);
					int visitCount = rs.getInt(5);
					int boardCount = rs.getInt(6);
					int commentCount = rs.getInt(7);
					String gender = rs.getString(8);
					boards.add(new MemberVo(memNo ,nick, gender, joinDate, boardCount, commentCount, visitCount, grade));
				}

			} catch (Exception e) {
				throw e;
			} finally {
				try {
					
					if (conn != null) conn.close();
				} catch (Exception e2) {
					throw e2;
				}
			}
			return boards;
		}
		
				// 총 멤버 수를 구하다
				public int selectTotalMemberCount() throws Exception {
					Connection conn = null;
					Statement stmt = null;
					ResultSet rs = null;
					int count = 0;
					try {
						conn = DBConn.getConnection();
						stmt = conn.createStatement();
						
						StringBuffer sql = new StringBuffer();
						sql.append("select count(*) from member;");
						
						rs = stmt.executeQuery(sql.toString());
						if(rs.next()) {
							count = rs.getInt(1);
						}
					} catch (Exception e) {
						throw e;
					}finally {
						try {				
							if (stmt != null) stmt.close();
							if (conn != null) conn.close();
						} catch (Exception e2) {
							throw e2;
						}
					}	
					
					return count;
				}
}
