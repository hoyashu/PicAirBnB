package controller.board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.FileUploadUtils;

@WebServlet("/fileDownload")
public class FileDownloadServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		//1. 시스템 파일명과 오리지널 파일명을 구한다.
		String originalFileName = request.getParameter("originalFileName");
		String systemFileName = request.getParameter("systemFileName");
		
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			//2. 파일 존재 유무를 확인한다.
			File file = new File(FileUploadUtils.UPLOAD_PATH + "/" + systemFileName);
			//System.out.println(file.exists());
			
			if (file.exists()) {
				response.setHeader("Content-Type", "application/octet-stream");
				response.setHeader("Content-Transfer-Encoding", "binary");
				originalFileName =  new String(originalFileName.getBytes("utf-8"), "ISO_8859_1");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFileName + "\"");
				int len = (int)file.length();
				response.setContentLength(len);		
				
				String filePath = FileUploadUtils.UPLOAD_PATH + "/" + systemFileName;
				bis = new BufferedInputStream(new FileInputStream(filePath));
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buf = new byte[1024];
				int readByte = 0;
				while ((readByte = bis.read(buf)) != -1) {
					bos.write(buf, 0, readByte);
				}
			}
			else {
				throw new Exception("파일이 존재하지 않습니다.");
			}
			
		} catch (Exception e) {
			request.setAttribute("exception", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		} finally {
			try {
				if (bis != null) bis.close();
				if (bos != null) bos.close();
			} catch (Exception e2) {
				request.setAttribute("exception", e2);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}

}



















