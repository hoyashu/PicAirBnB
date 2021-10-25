package controller.board;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import domain.AttachVo;
import domain.PostVo;
import domain.ReviewVo;
import model.service.PostService;
import util.FileUploadUtils;


@MultipartConfig(fileSizeThreshold = 1024, maxFileSize = -1L, maxRequestSize = -1L, location = "C:\\temp")
@WebServlet(urlPatterns={"/uploadFile"})
public class FileUploadServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		try {

			request.setCharacterEncoding("UTF-8");
			Collection<Part> parts = request.getParts();

			PostVo post = new PostVo();
			ReviewVo review = new ReviewVo();
			
			
			// 수정 필요 : 이 글을 작성하는 작성자(로그인 정보를 통해 받아와야 함)
			post.setWriterNo(1);
			
			
			for (Part part : parts) {
				if (!part.getHeader("Content-Disposition").contains("filename=")) {

					String name = part.getName();
					
					if (name.equals("subject")) {
						post.setSubject(request.getParameter(name));
					} else if (name.equals("bordNo")) {
						post.setBoardNo( Integer.parseInt(request.getParameter(name)));
					} else if (name.equals("content")) {
						post.setContent(request.getParameter(name));
					} else if (name.equals("tag")) {
						post.setTag(request.getParameter(name));
					} else if (name.equals("room")) {
						review.setRoomNo(Integer.parseInt(request.getParameter(name)));
					} else if (name.equals("rate_loc")) {
						review.setRate_loc(Integer.parseInt(request.getParameter(name)));
					} else if (name.equals("rate_clean")) {
						review.setRate_clean(Integer.parseInt(request.getParameter(name)));
					} else if (name.equals("rate_comu")) {
						review.setRate_comu(Integer.parseInt(request.getParameter(name)));
					} else if (name.equals("rate_chip")) {
						review.setRate_chip(Integer.parseInt(request.getParameter(name)));
					} else if (name.equals("visitDate")) {
						review.setVisitDate(request.getParameter(name));
					} else if (name.equals("re_push_pl")) {
						review.setRecommendPlace(request.getParameter(name));
					} else if (name.equals("re_push_npl")) {
						review.setNotRecommendPerson(request.getParameter(name));
					}

				} else {
				
					if(part.getSize() != 0) {
						AttachVo file = FileUploadUtils.upload(part, request);
						post.addAttach(file);
					}
				}
			}	
			

			PostService postService = PostService.getInstance();
			postService.registerPost(post,review);
			
			response.sendRedirect(request.getContextPath() + "/listPost.do");

		} catch (Exception e) {
			request.setAttribute("exception", e);
			request.setAttribute("requestURI", request.getRequestURI());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}

	}

}