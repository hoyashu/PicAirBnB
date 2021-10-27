package util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

@WebFilter(value="/*", initParams = {@WebInitParam(name="encoding", value="utf-8")})
// value="/*" ==> ��� Ŭ���� ��û�� ���ؼ� ���ڵ� ���͸� �����Ѵ�.

public class EncodingFilter implements Filter {
	private String encoding;
	
	/* FilterConfig 
	 * ���Ͱ� �ʱ�ȭ �Ǵ� ������ ȣ��Ǵ� init() �޼����� �Ű������� ���޵Ǵ� ��ü
	 * web.xml���� <init-param> �±׸� ���� �ۼ��ص� ������ ������� ServletContext�� ���� ������ ������ ����
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		/* getInitParameter() ServletConfig�� �ż���
		 * �̸� web.xml ���ٰ� �����ص� �Ķ���͵��� ���� ��� �� �� �ֽ��ϴ�.*/
		this.encoding = fConfig.getInitParameter("encoding"); //utf-8
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		/* getMethod() HttpServletRequest�� �ż���
		 * request.getMethod().equals("GET") �� ��� GET ���� �Ѿ���� ��쿣 true�� ��ȯ
		 * request.getMethod().equals("POST") �� ��� POST �� �Ѿ���� ��쿣 true�� ��ȯ
		 */

		/* qualsIgnoreCase() equals�� ��ҹ��� ���� / equalsIgnoreCase�� ��ҹ��� ����x*/
		//��û request�� post�� ��� xml�� encoding�̶� �̸����� �����ص� �Ķ���� ��(utf-8)���� ���ڵ�����
		if (((HttpServletRequest) request).getMethod().equalsIgnoreCase("post")) {
			request.setCharacterEncoding(encoding); //���� post�� �޾ƿö� �ϴ� ���ڵ� ��İ� ����
		}

		/* doFilter()  ��û(Request)�� ����(Response)���� ü���� ����� ������ �����̳ʿ��� ȣ��˴ϴ�.*/
		chain.doFilter(request, response);
	}

}
