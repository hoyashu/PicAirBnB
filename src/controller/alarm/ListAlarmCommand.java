package controller.alarm;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.AlarmVo;
import model.dao.alarm.AlarmDao;

public class ListAlarmCommand implements Command {
	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {    
		// 1.추가할 알림 정보를 받아온다.
		int memNo = Integer.parseInt(req.getParameter("memNo"));
		
		//2. 세션에 저장된 회원no와 같은지 비교할 것
		
		
		try {
			// 3. DB에서 알림을 조회한다.
			AlarmDao alarmDao = AlarmDao.getInstance();
			List<AlarmVo> alarms = alarmDao.selectAlarmList(memNo);
			
			// 4. 알림 목록을 불러옴과 동시에 읽음처리로 변경한다.
			alarmDao.updateReadAlarmList(memNo);
			
			//결과를 셋팅한다
			req.setAttribute("alarmList", alarms);
			
			// 결과를 json으로 보낸다.
			return new ActionForward("/views/alarm_list_ajax.jsp", false);

		} catch (Exception e) {
			req.setAttribute("exception", e);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
			dispatcher.forward(req, res);
			return null;
		}
	}
}
