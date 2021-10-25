package model.dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import domain.MemberStateVo;
import domain.MemberVo;
import util.DBConn;

public class MemberStateDao {
	// singleton pattern
			private static MemberStateDao MemberStateDao;
			
			private MemberStateDao() {
				
			}
			
			public static MemberStateDao getInstance() {
				if (MemberStateDao == null) {
					MemberStateDao = new MemberStateDao();
				}
				return MemberStateDao;
			}
			
			// 게시글 목록을 조회한다
			public ArrayList<MemberStateVo> selectMemberStateList(int startRow, int postSize) throws Exception {
				ArrayList<MemberStateVo> boardstate = new ArrayList<MemberStateVo>();
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					conn = DBConn.getConnection();

					StringBuffer sql = new StringBuffer();
					sql.append("SELECT ms_no, mb_no, ms_reason, ms_datetime   ");
					sql.append("FROM memberstate  ");
					sql.append("ORDER BY ms_no DESC LIMIT ? OFFSET ?; ");
		
					pstmt = conn.prepareStatement(sql.toString());
					
					pstmt.setInt(1, postSize);
					pstmt.setInt(2, startRow);
					
					rs = pstmt.executeQuery();

					while (rs.next()) {
						int stateNo = rs.getInt(1);
						int memNo = rs.getInt(1);
						String reason = rs.getString(2);
						String withdrawDate = rs.getString(4);
						
						boardstate.add(new MemberStateVo(stateNo, memNo, reason, withdrawDate));
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
				return boardstate;
			}
			
					// 총 멤버 수를 구하다
					public int selectTotalMemberStateCount() throws Exception {
						Connection conn = null;
						Statement stmt = null;
						ResultSet rs = null;
						int count = 0;
						try {
							conn = DBConn.getConnection();
							stmt = conn.createStatement();
							
							StringBuffer sql = new StringBuffer();
							sql.append("select count(*) from memberstate;");
							
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
