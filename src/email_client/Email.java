package email_client;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Email implements Serializable{
    
    private String content;
    private String subject;
    private String emailAddress;
    private String Date;
    
    public Email(String emailAddress,String subject,String content){
        this.content = content;
        this.emailAddress = emailAddress;
        this.subject = subject;
        
        //Creating the date when the email is created.
        Date thisDate = new Date();
        SimpleDateFormat date = new SimpleDateFormat("YYYY/MM/dd");
        
        //Assigning date to the Date variable
        this.Date = date.format(thisDate);
        
    }
    
    public String getContent(){
        //Getter method for content
        return content;
    }
    
    public String getSubject(){
        //getter method for subject
        return subject;
    }
    
    public String getEmailAddress(){
        //getter method for email address
        return emailAddress;
    }
    
    public String getDateOfSendingMail(){
        //getter method for the date of sending the email
        return Date;
    }
}
