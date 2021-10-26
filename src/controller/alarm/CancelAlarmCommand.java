package controller.alarm;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.dao.alarm.AlarmDao;

public class CancelAlarmCommand implements Command {
	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {   
		System.out.println("������û");
		// 1.�߰��� �˸� ������ �޾ƿ´�.
		int alarmNo = Integer.parseInt(req.getParameter("alarmNo"));
		System.out.println("no"+alarmNo);
		int memNo = Integer.parseInt(req.getParameter("memNo"));
		System.out.println("memNo"+memNo);
		//2. ���ǿ� ����� ȸ��no�� ������ ���� ��
		System.out.println("������û����");
		
		try {
			// 3. DB���� Ư�� �˸��� �����Ѵ�.
			AlarmDao alarmDao = AlarmDao.getInstance();
			alarmDao.delectAlarm(alarmNo);
			
			//����� �����Ѵ�
			req.setAttribute("result", "OK");
			
			System.out.println("������û");
			// ����� json���� ������.
			return new ActionForward("/views/alarm_delect_ajax.jsp", false);

		} catch (Exception e) {
			req.setAttribute("exception", e);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
			dispatcher.forward(req, res);
			return null;
		}
	}
}
