package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(value = "/jsp/*")
public class AuthFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		String userId = (String) session.getAttribute("userId");

		// ��ȸ�� ���ٽ� �α����������� �̵�
		if (userId != null) {
			chain.doFilter(request, response);
		} else {
			HttpServletRequest req = ((HttpServletRequest) request);
			String url = req.getServletPath();

			String query = ((HttpServletRequest) request).getQueryString();
			if (query == null) {
				url = "";
			} else {
				url += "?" + query;
			}

			// ��û���� setAttribute�� ��ûurl�ּҸ� �����Ѵ�.
			request.setAttribute("url", url);
			
			// ������ setAttribute�� ���� ���������� ����ϱ����� ������ ����� ����Ѵ�.
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
