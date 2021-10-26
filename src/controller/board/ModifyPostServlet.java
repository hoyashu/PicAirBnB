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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import domain.AttachVo;
import domain.PostVo;
import domain.ReviewVo;
import model.service.PostService;
import util.FileUploadUtils;

@MultipartConfig(fileSizeThreshold = 1024, location = "C:\\temp", maxFileSize = -1L, maxRequestSize = -1L)
@WebServlet("/modifyPost")
public class ModifyPostServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {			
			request.setCharacterEncoding("utf-8");
			
			//세션 영역에 저장된 게시글 정보를 구한다.
			HttpSession session = request.getSession();
			PostVo post = (PostVo) session.getAttribute("post");
			ReviewVo review = new ReviewVo();
			if(post.getBoardNo()==1) {
				review = post.getReview();
			}

			// 게시글 정보에서 파일 목록을 비운다.
			post.getAttachList().clear();

			Collection<Part> parts = request.getParts();
			
			for (Part part : parts) {
				if (!part.getHeader("content-disposition").contains("filename=")) {
					String name = part.getName();
					switch (name) {
					case "subject":
						post.setSubject(request.getParameter(name));
						break;
					case "bordNo":
						post.setBoardNo(Integer.parseInt(request.getParameter(name)));
						break;
					case "content":
						post.setContent(request.getParameter(name));
						break;
					case "tag":
						post.setTag(request.getParameter(name));
						break;
					case "room":
						review.setRoomNo(Integer.parseInt(request.getParameter(name)));
						break;
					case "rate_loc":
						review.setRate_loc(Integer.parseInt(request.getParameter(name)));
						break;
					case "rate_clean":
						review.setRate_clean(Integer.parseInt(request.getParameter(name)));
						break;
					case "rate_comu":
						review.setRate_comu(Integer.parseInt(request.getParameter(name)));
						break;
					case "rate_chip":
						review.setRate_chip(Integer.parseInt(request.getParameter(name)));
						break;
					case "visitDate":
						review.setVisitDate(request.getParameter(name));
						break;
					case "re_push_pl":
						review.setRecommendPlace(request.getParameter(name));
						break;
					case "re_push_npl":
						review.setNotRecommendPerson(request.getParameter(name));
						break;
					}
					
				} else {
					if (part.getSize() != 0) {
						AttachVo file = FileUploadUtils.upload(part,request);
						post.addAttach(file);
					}
				}
			}		
			
			if(post.getBoardNo()==1) {
				post.setReview(review);
			}
			
			PostService postService = PostService.getInstance();
			postService.modifyPost(post);
			session.removeAttribute("post");		
			session.setAttribute("boardNo_MOD", post.getBoardNo());	
			response.sendRedirect(request.getContextPath() + "/listPost.do");
			
			
		} catch (Exception ex) {
			request.setAttribute("exception", ex);
			request.setAttribute("requestURI", request.getRequestURI());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}
	}
}






