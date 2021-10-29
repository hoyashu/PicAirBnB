package controller.member;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SMTPAuthenticator;
import model.service.MemberService;

@WebServlet(value = "/sendFindPwMail.do")
public class SendMemberMailCommand extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String id = req.getParameter("id");
		int result = 0;
		int code = 0;
				
		String name = "PicAirBnB";
		String email = "5126537@PicAirBnB.com";
		String title = "��й�ȣ ã�� �����ڵ�";
		

		try {

			MemberService service = MemberService.getInstance();
			int idOverlap = service.retrieveMailOverlapMember(id);

			if (idOverlap == 0) {
				result = 1;
			} else {
				//������ȣ ����
				Random r = new Random();
				code = r.nextInt(999999); // ������������
				
				String mailFrom = name + "<" + email + ">";
				String mailTo = id;
				String contents = "���� ��� :: " + name + "&lt;" + email + "&gt;<br><br>" + title + "<br><br>��й�ȣã��(����) ������ȣ�� " + code + " �Դϴ�.";

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

			}
			req.setAttribute("result", result);
			req.setAttribute("code", code);
			//HttpSession session = req.getSession();
			//session.setAttribute("code", code);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/member_sendFindPwEmailResult_ajax.jsp");
			dispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
