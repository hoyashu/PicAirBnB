package controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value = "/sendMail")
public class SMTPAuthCommand extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("zz");
		String userId = req.getParameter("userId");

		// DB���� userId�� �ش��ϴ� ȸ�������� ��ȸ�Ѵ�.

		String name = "�����";
		String email = "5126537@naver.com";
		String title = "�����ڵ��Դϴ�.";
		String text = "�����ڵ�";

		try {
			String mailFrom = name + "<" + email + ">";
			String mailTo = "�����<5126537@gmail.com>";
			String contents = "���� ��� :: " + name + "&lt;" + email + "&gt;<br><br>" + title + "<br><br>" + text;

			mailFrom = new String(mailFrom.getBytes("UTF-8"), "UTF-8");
			mailTo = new String(mailTo.getBytes("UTF-8"), "UTF-8");

			// ������ : SSL�� �̿��� ���� : 465
			// ������ : TLS�� �̿��� ���� : 587
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			

			Authenticator auth = new SMTPAuthenticator();

			Session sess = Session.getDefaultInstance(props, auth);
			sess.setDebug(true);

			MimeMessage msg = new MimeMessage(sess);

			msg.setFrom(new InternetAddress(mailFrom));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
			msg.setSubject(title, "UTF-8");
			msg.setContent(contents, "text/html; charset=UTF-8");
			msg.setHeader("Content-type", "text/html; charset=UTF-8");

			Transport.send(msg);		
			//���������� json�� jsp�� ������.
			//�׷��� ���ư�
			//�߼۵� ������

		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}
}

