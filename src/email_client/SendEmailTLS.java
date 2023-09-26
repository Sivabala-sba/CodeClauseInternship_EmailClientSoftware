
package email_client;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailTLS {
    
    public static void sendMail(String Reciever,String Subject,String Content) {

        String username = "amilakasun2000@gmail.com";
        String password = "acbarfvwgjczsxli";
        String reciever = Reciever;

        Properties prop = new Properties();
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(reciever));
            message.setSubject(Subject);
            message.setText(Content);

            Transport.send(message);

            System.out.println("Successfuly Sent the Email");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
}
