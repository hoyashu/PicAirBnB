package model.service;

import java.sql.Connection;
import java.util.List;

import domain.AlarmVo;
import util.DBConn;
import model.dao.alarm.AlarmDao;

public class AlarmService {

	private static AlarmService alarmService;

	private AlarmService() {

	}

	public static AlarmService getInstance() {
		if (alarmService == null) {
			alarmService = new AlarmService();
		}
		return alarmService;
	}

	// 알림을 발송한다
	public void registerAlarm(List<AlarmVo> alarm) throws Exception {
		Connection conn = null;
		boolean isSuccess = false;

		try {
			conn = DBConn.getConnection();

			// 커밋을 수동으로 조정할것이기에 false로 수정한다. (트렌젝션의 시작점)
			conn.setAutoCommit(false);

			// 1. 게시글 정보를 등록한다.
			AlarmDao alarmDao = AlarmDao.getInstance();
			// 커넥션이 동일해야지 트렌젹션 유지가 가능하기 때문에, insertBoard에 conn을 보낸것임
			alarmDao.insertAlarm(alarm, conn);

			// 위에 코드에 문제가 없다면 아래 코드까지 실행되어 isSuccess = true가 된다.
			// 만약 오류가 있었다면 try구문에서 바로 나가졌을 것임
			isSuccess = true;

		} catch (Exception e) {

			throw e;

		} finally {

			try {
				// 접속이 성공된 경우
				if (conn != null) {
					// 만약 코드가 끝까지 실행되어 upload가 정상적으로 이루어졌다면
					if (isSuccess) {
						// sql코드를 커밋한다.
						conn.commit();
					} else {
						// sql코드를 롤백한다.
						conn.rollback();
					}
					conn.close();
				}

			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	// 회원의 알림 목록을 조회한다.
	public List<AlarmVo> retrievAlarmList(int mbNo) throws Exception {
		AlarmDao alarmDao= AlarmDao.getInstance();
		
		//회원이 알림 목록을 전체 조회한다.
		List<AlarmVo> alarms = alarmDao.selectAlarmList(mbNo);
		
		//조회와 동시에 알림 읽음 처리가 된다.
		alarmDao.updateReadAlarmList(mbNo);
		
		return alarms;

	}
	
	// 회원의 미열람 상태인 알림의 개수를 조회한다.
	public int retrieveNoReadAlarmCount(int mbNo) throws Exception {
		return AlarmDao.getInstance().selectNoReadAlarmCount(mbNo);
	}

	// 조건에 해당하는 알림을 삭제한다.
	public void removeAlarm(int alarmNo) throws Exception {
		AlarmDao.getInstance().delectAlarm(alarmNo);
	}

	// 조건에 해당하는 알림을 전체 삭제한다.
	public void removeAllAlarm(int memNo) throws Exception {
		AlarmDao.getInstance().delectAllAlarm(memNo);
	}

}
