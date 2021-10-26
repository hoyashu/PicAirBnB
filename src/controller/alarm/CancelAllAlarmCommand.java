package controller.alarm;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.dao.alarm.AlarmDao;

public class CancelAllAlarmCommand implements Command {
	@Override
	public ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 1.�߰��� �˸� ������ �޾ƿ´�.
		int memNo = Integer.parseInt(req.getParameter("memNo"));

		// 2. ���ǿ� ����� ȸ��no�� ������ ���� ��

		try {
			// 3. DB���� Ư�� �˸��� �����Ѵ�.
			AlarmDao alarmDao = AlarmDao.getInstance();
			alarmDao.delectAllAlarm(memNo);

			// ����� �����Ѵ�
			req.setAttribute("result", "OK");

			// ����� json���� ������.
			return new ActionForward("/views/alarm_delect_all_ajax.jsp", false);

		} catch (Exception e) {
			req.setAttribute("exception", e);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
			dispatcher.forward(req, res);
			return null;
		}
	}
}
