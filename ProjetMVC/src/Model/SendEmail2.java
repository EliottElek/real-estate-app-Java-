package Model;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/*
This class a a derivated class from a programm found on the internet.
this is the link : https://www.journaldev.com/2532/javamail-example-send-mail-in-java-smtp
*/
public class SendEmail2
{
 
 public SendEmail2(String mail, String subject, String content) throws Exception {
        System.out.println("SENDING PROCESS");

        final String username = "mp2eceparis@gmail.com";
        final String password = "ECEParis12345";
        String fromEmail = "mp2eceparis@gmail.com";
        String toEmail = mail;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(content);
        } catch (Exception ex) {
            Logger.getLogger(SendEmail2.class.getName()).log(Level.SEVERE, null, ex);
        }
        Transport.send(message);
        System.out.println("MESSAGE SENT SUCCESSFULLY");
    }

}
