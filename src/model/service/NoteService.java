package model.service;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import domain.NoteVo;
import model.dao.note.NoteDao;
import util.DBConn;

public class NoteService {
	private static NoteService service;

	private NoteService() {

	}

	public static NoteService getInstance() {
		if (service == null) {
			service = new NoteService();
		}

		return service;
	}

	// 쪽지 작성
	public void registerNote(String userId, String noteCon, String[] getMbIdArray) throws Exception {
		Connection conn = null;
		boolean isSuccess = false;
		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false);

			NoteDao notedao = NoteDao.getInstance();
			// 쪽지내용 저장하고 쪽지 번호 구하기
			int noteNo = notedao.insertNote(noteCon, conn);

			// 유저 멤버 번호 구하기
			int sendMbno = notedao.selectMbno(userId, conn);

			// noteindex 저장하기
			for (String getMbId : getMbIdArray) {
				int getMbno = notedao.selectMbno(getMbId, conn);
				notedao.insertNoteIndex(noteNo, sendMbno, getMbno, conn);
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
			} catch (Exception e2) {
				throw e2;
			}
		}

	}

	// 받은 쪽지 목록 조회
	public ArrayList<NoteVo> retrieveReceiveNoteList(String userId, int startRow, int postSize) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int getMbno = notedao.selectMbno(userId);
		ArrayList<NoteVo> getNoteList = notedao.selectReceiveNoteList(getMbno, startRow, postSize);
		return getNoteList;
	}

	// 보낸 쪽지 목록 조회
	public ArrayList<NoteVo> retrieveSendNoteList(String userId, int startRow, int postSize) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int sendMbno = notedao.selectMbno(userId);
		ArrayList<NoteVo> sendNoteList = notedao.selectSendNoteList(sendMbno, startRow, postSize);
		return sendNoteList;
	}

	// 보관함 목록 조회
	public ArrayList<NoteVo> retrieveSaveNoteList(String userId, int startRow, int postSize) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int saveMbno = notedao.selectMbno(userId);
		ArrayList<NoteVo> saveNoteList = notedao.selectSaveNoteList(saveMbno, startRow, postSize);
		return saveNoteList;
	}

	// 받은 쪽지 갯수 구하기
	public int retrieveGetNoteCount(String userId) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int getMbno = notedao.selectMbno(userId);
		int receiveCount = notedao.selectReceiveNoteCount(getMbno);
		return receiveCount;
	}

	// 보낸 쪽지 갯수 구하기
	public int retrieveSendNoteCount(String userId) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int getMbno = notedao.selectMbno(userId);
		int receiveCount = notedao.selectReceiveNoteCount(getMbno);
		return receiveCount;
	}

	// 보관함 쪽지 갯수 구하기
	public int retrieveSaveNoteCount(String userId) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int Mbno = notedao.selectMbno(userId);
		int sendSaveNoteCount = notedao.selectSendSaveNoteCount(Mbno);
		int retrieveSaveNoteCount = notedao.selectRetrieveSaveNoteCount(Mbno);
		int saveNoteCount = sendSaveNoteCount + retrieveSaveNoteCount;
		return saveNoteCount;
	}

	// 선택된 수신쪽지 삭제처리하기
	public void removeRetrieveNotes(String userId, int[] noteNos) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int getMbno = notedao.selectMbno(userId);
		System.out.println(getMbno);
		for (int noteNo : noteNos) {
			notedao.deleteReceiveNotes(getMbno, noteNo);
		}
	}

	// 선택된 발신쪽지 삭제처리하기
	public void removeSendNotes(String userId, int[] noteNos) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int getMbno = notedao.selectMbno(userId);

		for (int noteNo : noteNos) {
			notedao.deleteSendNotes(getMbno, noteNo);
		}
	}

	// 선택된 보관함 쪽지 삭제처리하기
	public void removeSaveNotes(String userId, int[] noteNos) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int mbNo = notedao.selectMbno(userId);

		for (int noteNo : noteNos) {
			notedao.deleteSaveReceiveNotes(mbNo, noteNo);
			notedao.deleteSaveSendNotes(mbNo, noteNo);
		}
	}

	// 선택된 받은쪽지 보관함으로 저장하기
	public void reviseSaveRetrieveNote(String userId, int[] noteNos) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int mbNo = notedao.selectMbno(userId);
		for (int noteNo : noteNos) {
			notedao.updateSaveRetrieveNotes(mbNo, noteNo);
		}
	}

	// 선택된 보낸쪽지 보관함으로 저장하기
	public void reviseSaveSendNote(String userId, int[] noteNos) throws Exception {
		NoteDao notedao = NoteDao.getInstance();
		int mbNo = notedao.selectMbno(userId);
		System.out.println("작동");
		for (int noteNo : noteNos) {
			notedao.updateSaveSendNotes(mbNo, noteNo);
		}
	}
}
