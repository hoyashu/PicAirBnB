package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//redirect�� �̵���ų��, forward�� ������ �����Ҽ� �ִ� ���

//���ϸ�.do�� ȣ��� ���, �ش� ������ ����ȴ�.
//���⸦ ��ġ�� �ϱ����ؼ� .do�� ���°��̴�.
@WebServlet("*.do")
public class ControllerServlet extends HttpServlet {
	
	//�⺻������ get�����
	@Override
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�޾ƿ� request, response ����
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	//get���� ���ü��� post�� ���ü��� �ֱ⿡ �ߺ��� ���ϱ� ���ؼ� �̷��� ��
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String requestURI = request.getRequestURI();
			String contextPath = request.getContextPath();
			String commandURI = requestURI.substring(contextPath.length()); //writeBoardForm.do
			//�Ʒ� �ַ� �ص� ������ ��� ����
			//String commandURI2 = request.getServletPath();
			
			System.out.println(requestURI);
			System.out.println(contextPath);
			System.out.println(commandURI);

			//CommandFactory�� ����� ��ɵ��� �ҷ�����
			CommandFactory factory = CommandFactory.getInstance();
			
			// commandURI -> CommandFactory.java
			Command command = factory.createCommand(commandURI);
			//command -> "controller.WriteBoardFormCommand"
			
			//"controller.WriteBoardFormCommand"�� excute(����)��Ŵ
			ActionForward forward = command.excute(request, response);
			//forward�Ҳ��� redirect�Ұ��� boolean���� ������
			
			
			//���� forward�Ҳ��� redirect�� �����Ŵ
			if (forward.isRedirect()) { // redirect���
				
				response.sendRedirect(request.getContextPath() + forward.getPath());
				
			} else {// forward���
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
				
			}

			
		} catch (Exception ex) { //WriteBoardCommand.excute���� ���ƿ� ������ ����� ���ƿ�
			ex.printStackTrace();
			/*
			 * request.setAttribute("execption", ex); //error.jspfh ex�� �������� //������ ������ ���ؼ�
			 * //forward��� ��� RequestDispatcher dispatcher =
			 * request.getRequestDispatcher("/error.jsp"); dispatcher.forward(request,
			 * response);
			 */
			 
		}
	}

}
