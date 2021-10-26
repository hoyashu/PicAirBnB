package controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {

	public SMTPAuthenticator() {

        super();

    }

 

    public PasswordAuthentication getPasswordAuthentication() {
        String username = "5126537@gmail.com";
        String password = "waguri0410!!";
        return new PasswordAuthentication(username, password);

    }

} 