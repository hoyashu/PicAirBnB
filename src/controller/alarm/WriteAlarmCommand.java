package controller.alarm;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.AlarmVo;
import util.DBConn;
import model.dao.alarm.AlarmDao;

public class WriteAlarmCommand implements Command {

	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {    
		// 1.�߰��� �˸� ������ �޾ƿ´�.
		String alarmType = req.getParameter("alarmType");
		String alarmContent = req.getParameter("alarmContent");
		String alarmUrl = req.getParameter("alarmUrl");
		String[] arr = req.getParameterValues("memNo[]");
		int[] memNo = null;
		if (arr != null) {
			memNo = new int[arr.length];
			for (int i = 0; i < arr.length; i++) {
				memNo[i] = Integer.parseInt(arr[i]);
			}
		}
		
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			
			// 2. �˸��� DB�� �߰��Ѵ�
			AlarmDao alarmDao = AlarmDao.getInstance();

			ArrayList<AlarmVo> alarms = new ArrayList<AlarmVo>();
			for (int i = 0; i < memNo.length; i++) {
				alarms.add(new AlarmVo(alarmType, alarmContent, alarmUrl, memNo[i]));
			}

			//�߰��� �˸� ����
			int resultCount = alarmDao.insertAlarm(alarms, conn);
			req.setAttribute("resultCount", resultCount);

			// ����� json���� ������.
			return new ActionForward("/views/alarm_send_ajax.jsp", false);

		} catch (Exception e) {
			req.setAttribute("exception", e);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
			dispatcher.forward(req, res);
			return null;
		}
	}

}
