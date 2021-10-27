package util;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import domain.AttachVo;


public class FileUploadUtils {
	
	public static String UPLOAD_PATH = "";
	
	public static AttachVo upload(Part part, HttpServletRequest request) throws Exception  {
		UPLOAD_PATH = request.getServletContext().getRealPath("/upload");
		System.out.println(UPLOAD_PATH);
		
		String originalFileName = part.getSubmittedFileName();
		
		File file = new File(UPLOAD_PATH + "/" + originalFileName);
		String systemFileName = "";
		if (file.exists()) {
			systemFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "_" +
				UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
			
		} else {
			systemFileName = originalFileName;
		}
		
		int fileSize = (int)part.getSize();
		
		part.write(UPLOAD_PATH + "/" + systemFileName);
		part.delete();
		
		AttachVo attach = new AttachVo(originalFileName, systemFileName, fileSize);

		String fileType = part.getContentType();
		System.out.println("fileType - Ÿ�Ժ��� �з��ϴ� �ڵ� �ۼ� �ʿ�, �ٵ� ���� �ʿ� �ֳ� ����");
		System.out.println(fileType);
		
		return attach;
		
	}
	

}




















