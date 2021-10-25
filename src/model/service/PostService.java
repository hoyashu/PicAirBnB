package model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import domain.AttachVo;
import domain.PostVo;
import domain.ReviewVo;
import model.dao.board.AttachDao;
import model.dao.board.PostDao;
import model.dao.board.ReviewDao;
import util.DBConn;

public class PostService {

	// single pattern
	private static PostService service;

	private PostService() {

	}

	public static PostService getInstance() {
		if (service == null) {
			service = new PostService();
		}
		return service;
	}

	// 게시글 정보를 등록하다.
	public void registerPost(PostVo post, ReviewVo review) throws Exception {
		Connection conn = null;
		boolean isSuccess = false;
		try {
			conn = DBConn.getConnection();
			// tx.begin
			conn.setAutoCommit(false);

			// 1.게시글 정보를 등록한다.
			PostDao postDao = PostDao.getInstance();
			int no = postDao.insertpost(post, conn);
			
			
			if(post.getBoardNo()==1) {
				// 리뷰 정보를 등록한다.
				System.out.println("리뷰정보있을때");
				review.setNo(no);
				ReviewDao reviewDao = ReviewDao.getInstance();
				reviewDao.insertReview(review, conn);
				
			}

			// 2.파일 정보를 등록하다.
			AttachDao attachDao = AttachDao.getInstance();

			for (AttachVo attach : post.getAttachList()) {
				attach.setPostNo(no);
				attachDao.insertPostAttach(attach, conn);
			}
			isSuccess = true;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) {
					if (isSuccess) {
						conn.commit();
					} else {
						conn.rollback();
					}
					conn.close();
				}
			} catch (Exception ex) {
				throw ex;
			}
		}
	}
//	
//
	// 게시글 상세정보를 조회하다.
	public PostVo retrievePost(int no) throws Exception {
		
		PostDao postDao = PostDao.getInstance();
		postDao.upHitcount(no);
		return postDao.selectPost(no);
	}
	
	// 게시글정보를 변경하다.
	public void modifyPost(PostVo post) throws Exception {
		// 트랜잭션 처리
		// 게시글 정보를 변경하다.
		// 첨부된 파일이 존재하는 경우 파일을 등록한다.
		boolean isSuccess = false;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);

			PostDao postDao = PostDao.getInstance();
			postDao.updatePost(post, conn);	
			
			if(post.getBoardNo()==1) {
				// 리뷰 정보를 변경한다.
				ReviewDao reviewDao = ReviewDao.getInstance();
				reviewDao.updateReview(post.getReview(), conn);
				
			}

			AttachDao attachDao = AttachDao.getInstance();
			
			for (AttachVo attach : post.getAttachList()) {
				attach.setPostNo(post.getNo());
				attachDao.insertPostAttach(attach, conn);
			}

			isSuccess = true;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) {
					if (isSuccess) {
						conn.commit();
					} else {
						conn.rollback();
					}
				}

			} catch (Exception e2) {
				throw e2;
			}
		}
	}
	
	public void removePostAttach(int attachNo) throws Exception {

		// DB에서 해당하는 첨부 파일을 삭제한다.
		boolean isSuccess = false;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);

			AttachDao attachDao = AttachDao.getInstance();
			attachDao.deletePostAttach(attachNo, conn);
			
			isSuccess = true;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) {
					if (isSuccess) {
						conn.commit();
					} else {
						conn.rollback();
					}
				}

			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// 게시글 정보를 삭제하다.
	public void removePost(int postNo) throws Exception {
		// 트랜잭션 처리
		// DB에서 게시글에 해당하는 파일 정보를 삭제한다.
		// DB에서 게시글 정보를 삭제한다.
		boolean isSuccess = false;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);

			AttachDao attachDao = AttachDao.getInstance();
			attachDao.deleteAttachbyPost(postNo, conn);

			PostDao postDao = PostDao.getInstance();
			postDao.deletePost(postNo, conn);

			isSuccess = true;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (conn != null) {
					if (isSuccess) {
						conn.commit();
					} else {
						conn.rollback();
					}
				}

			} catch (Exception e2) {
				throw e2;
			}
		}
	}
//	
	
	//게시글 목록을 조회하다.
	public ArrayList<PostVo> retrievePostSearchList(HashMap<String,String> map, int startRow, int postSize) throws Exception {
		PostDao postDao = PostDao.getInstance();
		return postDao.selectPostSearchList(map,startRow, postSize);		
	}
	
	//총 게시글 수를 구하다.
	public int retrievePostSearchListCount() throws Exception {
		PostDao postDao = PostDao.getInstance();
		return postDao.selectTotalPostCount();
	}	

}

















