package controller.alarm;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.dao.alarm.AlarmDao;

public class CountNoReadAlarmCommand implements Command {
	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {    
		
		// 1.추가할 알림 정보를 받아온다.
		int memNo = Integer.parseInt(req.getParameter("memNo"));
		
		//2. 세션에 저장된 회원no와 같은지 비교할 것
		
		
		try {
			// 3. DB에서 알림을 조회한다.
			AlarmDao alarmDao = AlarmDao.getInstance();
			int resultCount = alarmDao.selectNoReadAlarmCount(memNo);
			
			//결과를 셋팅한다
			req.setAttribute("resultCount", resultCount);
			
			// 결과를 json으로 보낸다.
			return new ActionForward("/views/alarm_count_ajax.jsp", false);

		} catch (Exception e) {
			req.setAttribute("exception", e);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
			dispatcher.forward(req, res);
			return null;
		}
	}
}
