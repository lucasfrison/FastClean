package com.lavanderia.utils;

import jakarta.mail.Authenticator;
import java.util.Date;

import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {

	/**
	 * Utility method to send simple HTML email
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public static void sendEmail(Session session, String toEmail, String subject, String body){
            try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("fastclean156@gmail.com", "FastClean"));

	      msg.setReplyTo(InternetAddress.parse("fastclean156@gmail.com", false));

	      msg.setSubject(subject, "UTF-8");

	      msg.setText(body, "UTF-8");

	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      System.out.println("Message is ready");
              Transport.send(msg);  

	      System.out.println("Email Sent Successfully!!");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}
        
        public static void preparaEmail(String toEmail, String password) {
            String smtpHostServer = "smtp.gmail.com";
            String boasVindas = 
                    "Bem vindo à FastClean, aqui está sua senha de acesso na plataforma de atendimento: \n Senha: ";

            Properties props = System.getProperties();

            props.put("mail.smtp.host", smtpHostServer);
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.starttls.enable","true");
                props.put("mail.smtp.auth", true);

                Authenticator auth = new Authenticator() {
                    //override the getPasswordAuthentication method
                    protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("fastclean156@gmail.com", "qdgftlktrtwolbxo");
                    }
                };

            Session session = Session.getInstance(props, auth);

            sendEmail(session, toEmail,"Nova senha para FastClean", boasVindas + password);
        }
}