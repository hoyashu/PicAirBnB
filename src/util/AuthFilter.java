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

		// 비회원 접근시 로그인페이지로 이동
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

			// 요청값에 setAttribute로 요청url주소를 저장한다.
			request.setAttribute("url", url);
			
			// 설정한 setAttribute를 다음 페이지에서 사용하기위해 포워딩 방식을 사용한다.
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
