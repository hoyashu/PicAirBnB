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
		// 1.�߰��� �˸� ������ �޾ƿ´�.
		int memNo = Integer.parseInt(req.getParameter("memNo"));
		
		//2. ���ǿ� ����� ȸ��no�� ������ ���� ��
		
		
		try {
			// 3. DB���� �˸��� ��ȸ�Ѵ�.
			AlarmDao alarmDao = AlarmDao.getInstance();
			List<AlarmVo> alarms = alarmDao.selectAlarmList(memNo);
			
			// 4. �˸� ����� �ҷ��Ȱ� ���ÿ� ����ó���� �����Ѵ�.
			alarmDao.updateReadAlarmList(memNo);
			
			//����� �����Ѵ�
			req.setAttribute("alarmList", alarms);
			
			// ����� json���� ������.
			return new ActionForward("/views/alarm_list_ajax.jsp", false);

		} catch (Exception e) {
			req.setAttribute("exception", e);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
			dispatcher.forward(req, res);
			return null;
		}
	}
}
