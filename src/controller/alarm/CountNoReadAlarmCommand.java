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
		
		// 1.�߰��� �˸� ������ �޾ƿ´�.
		int memNo = Integer.parseInt(req.getParameter("memNo"));
		
		//2. ���ǿ� ����� ȸ��no�� ������ ���� ��
		
		
		try {
			// 3. DB���� �˸��� ��ȸ�Ѵ�.
			AlarmDao alarmDao = AlarmDao.getInstance();
			int resultCount = alarmDao.selectNoReadAlarmCount(memNo);
			
			//����� �����Ѵ�
			req.setAttribute("resultCount", resultCount);
			
			// ����� json���� ������.
			return new ActionForward("/views/alarm_count_ajax.jsp", false);

		} catch (Exception e) {
			req.setAttribute("exception", e);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
			dispatcher.forward(req, res);
			return null;
		}
	}
}
