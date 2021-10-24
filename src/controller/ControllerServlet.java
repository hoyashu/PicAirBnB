package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//redirect로 이동시킬지, forward로 보낼지 선택할수 있는 기능

//파일명.do로 호출된 경우, 해당 파일이 실행된다.
//여기를 거치게 하기위해서 .do로 적는것이다.
@WebServlet("*.do")
public class ControllerServlet extends HttpServlet {
	
	//기본적으로 get방식임
	@Override
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//받아온 request, response 전달
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	//get으로 들어올수도 post로 들어올수도 있기에 중복을 피하기 위해서 이렇게 함
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String requestURI = request.getRequestURI();
			String contextPath = request.getContextPath();
			String commandURI = requestURI.substring(contextPath.length()); //writeBoardForm.do
			//아래 애로 해도 동일한 결과 나옴
			//String commandURI2 = request.getServletPath();
			
			System.out.println(requestURI);
			System.out.println(contextPath);
			System.out.println(commandURI);

			//CommandFactory에 저장된 기능들이 불러와짐
			CommandFactory factory = CommandFactory.getInstance();
			
			// commandURI -> CommandFactory.java
			Command command = factory.createCommand(commandURI);
			//command -> "controller.WriteBoardFormCommand"
			
			//"controller.WriteBoardFormCommand"를 excute(실행)시킴
			ActionForward forward = command.excute(request, response);
			//forward할껀지 redirect할건지 boolean값을 가져옴
			
			
			//실제 forward할껀지 redirect를 실행시킴
			if (forward.isRedirect()) { // redirect방식
				
				response.sendRedirect(request.getContextPath() + forward.getPath());
				
			} else {// forward방식
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
				
			}

			
		} catch (Exception ex) { //WriteBoardCommand.excute에서 날아온 오류가 여기로 날아옴
			ex.printStackTrace();
			/*
			 * request.setAttribute("execption", ex); //error.jspfh ex를 날려버림 //데이터 유지를 위해서
			 * //forward방식 사용 RequestDispatcher dispatcher =
			 * request.getRequestDispatcher("/error.jsp"); dispatcher.forward(request,
			 * response);
			 */
			 
		}
	}

}
