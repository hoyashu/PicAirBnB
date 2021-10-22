package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	//받아온 경로를 redirect or forward중에 뭐로 실행시킬건지에 대해 저장
	ActionForward excute(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
