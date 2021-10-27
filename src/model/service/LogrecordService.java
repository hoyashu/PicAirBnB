package model.service;

import java.util.ArrayList;

import domain.LogrecordVo;
import model.dao.statistic.LogrecordDao;

public class LogrecordService {
	
	private static LogrecordService service;
	
	private LogrecordService() {
		
	}
	
	public static LogrecordService getInstance() {
		if(service == null) {
			service = new LogrecordService();
		}
		
		return service;
	}
	
	//로그 리스트 조회
	public ArrayList<LogrecordVo> retrieveMemberLogList(int startRow, int postSize) throws Exception{
		LogrecordDao logrecordDao = LogrecordDao.getInstance();
		return logrecordDao.selectMemberLogRecord(startRow, postSize);
	}
	
	//전체 로그 기록 갯수 조회
	public int retrieveTotalLogrecordCount() throws Exception{
		LogrecordDao logrecordDao = LogrecordDao.getInstance();
		return logrecordDao.selectTotalLogrecordCount();
	}
	
	// 당일 로그 기록 갯수 조회
	public int retrieveTodayLogrecordCount() throws Exception{
		LogrecordDao logrecordDao = LogrecordDao.getInstance();
		return logrecordDao.selectTodayLogrecordCount();
	}
	
	//그래프 유형별 로그 기록 갯수 조회
	public ArrayList<Integer> retrieveTypeOfGraphLogrecordCount(int typeOfGraphNumber) throws Exception{
		ArrayList<Integer> typeOfGraphLecordCounts = new ArrayList<Integer>();
		if(typeOfGraphNumber == 7) {
			LogrecordDao logrecordDao = LogrecordDao.getInstance();
			typeOfGraphLecordCounts = logrecordDao.selectWeeklyLogrecordCount();
		} else if(typeOfGraphNumber == 30) {
			LogrecordDao logrecordDao = LogrecordDao.getInstance();
			typeOfGraphLecordCounts = logrecordDao.selectMonthlyLogrecordCount();
		}
		return typeOfGraphLecordCounts;
	}
	
	//그래프 유형별 게시글 갯수 조회
	public ArrayList<Integer> retrieveTypeOfGraphPostCount(int typeOfGraphNumber) throws Exception{
		ArrayList<Integer> typeOfGraphPostCounts = new ArrayList<Integer>();
		if(typeOfGraphNumber == 7) {
			LogrecordDao logrecordDao = LogrecordDao.getInstance();
			typeOfGraphPostCounts = logrecordDao.selectWeeklyPostCount();
		} else if(typeOfGraphNumber == 30) {
			LogrecordDao logrecordDao = LogrecordDao.getInstance();
			typeOfGraphPostCounts = logrecordDao.selectMonthlyPostCount();
		}
		return typeOfGraphPostCounts;
	}
	
	public void registerLogrecord(String userIp, String userId) throws Exception {
		LogrecordDao logrecordDao = LogrecordDao.getInstance();
		logrecordDao.insertLogrecord(userIp, userId);
	}
}











