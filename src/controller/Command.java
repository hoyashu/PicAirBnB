package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	//�޾ƿ� ��θ� redirect or forward�߿� ���� �����ų������ ���� ����
	ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
