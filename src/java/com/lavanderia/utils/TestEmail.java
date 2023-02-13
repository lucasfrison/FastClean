package com.lavanderia.utils;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import java.util.Properties;

import jakarta.mail.Session;

public class TestEmail {

    public TestEmail() {
    }
	
    public static void main(String[] args) {

        System.out.println("SimpleEmail Start");

        String smtpHostServer = "smtp.gmail.com";
        String emailID = "diagjames@gmail.com";

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

        EmailUtil.sendEmail(session, emailID,"Gay", "Não manja nada");
    }

}