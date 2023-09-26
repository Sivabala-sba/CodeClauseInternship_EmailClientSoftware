
package email_client;

//import java.net.PasswordAuthentication;
import javax.mail.Authenticator;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import java.util.Date;
import java.text.SimpleDateFormat;

public class MainOperartions {
    
    
    //--------------------------------------------------------------------------------------------------------------
    public void sendEmail(String emailAddress, String subject,String content){
        /*
        Method for sending the mail
        Here at the begining an Email object is created depending on the given details
        
        Parameters :
            emailAddress --> Email address of the reciever
            subject      --> Subject of the email
            content      --> Conetnt of the email
        */
        
        //Creating new email boject
        Email email = new Email(emailAddress,subject,content);
        
        
        
        //E-mail adddress of the recieveer
        String toAddress = email.getEmailAddress();
        
        //Subject of the E-mail
        String Subject = email.getSubject();
        
        //Content of the E-mail
        String Content = email.getContent();
        
        //Code for sending the email
        
        //Process for sending the email
        
        String username = "amilakasun2000@gmail.com";
        String password = "acbarfvwgjczsxli";
        String reciever = toAddress;

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
            //e.printStackTrace();
            System.out.println("Problem occured .. Check your internet connection");
        }
    
        
        //Adding every sending email to the EmailArray
        
        Email_Client.EmailArray.add(email);
        
        
    }
    //-------------------------------------------------------------------------------------------------------------------
    
    
    
    public ArrayList<Recipient> whoHasBirthdays(String Birthday){
        /*
        Generating an array of recipients who has birthday on the given date
        
        Parameters :
            Birthday --> Given date. We get an array of recipients who have birthdays, for this date
        */
        
        //Tempary array to store all recipient objects in the application
        ArrayList<Recipient> tempArray = Email_Client.RecipientsArray;
        //Year , Month and date of input birthday
        String [] inputDate = Birthday.split("/",-2);
        
        ArrayList<Recipient> birthdayRec = new ArrayList<Recipient>();
        
        for(int i = 0; i < tempArray.size(); i++){
            if(tempArray.get(i) instanceof Personal || tempArray.get(i) instanceof OfficialFriend){
                
                //Array containing the Year , Month , Date of birthday of relavent recipient onject
                String [] birthdayDetails = tempArray.get(i).getBirthday().split("/",-2);
                
                if(birthdayDetails[1].equals(inputDate[1]) && birthdayDetails[2].equals(inputDate[2])){
                    
                    birthdayRec.add(tempArray.get(i));
                    
                }
            }
        }
        
        return birthdayRec;
         
    }
    
    //-------------------------------------------------------------------------------------------------------------------
    
    public void addRecipient(String RecipientInfo){
        /*
        Method for adding recipient to the text file
        Here the details of a recipient is given in string format.
        
        parameters:
            RecipientInfo --> Details of recipient
        */
        
        // input format - Official: nimal,nimal@gmail.com,ceo
        
        //Getting the date in string format
        Date thisDate = new Date();
        SimpleDateFormat date = new SimpleDateFormat("YYYY/MM/dd");    
        String today = date.format(thisDate);
        
                      
        try {
            //Accessing ClientList text file
            FileWriter f = new FileWriter("C:\\Users\\Amila Kasun\\Desktop\\Edu\\UOM\\Academic\\Sem 2\\Program Construction\\Project Text Files\\ClientList.txt", true);
            
            //Checking whether the client is already in the list
            if(isInClientList(RecipientInfo)){
                System.out.println("Recipient is already in the data base");
            }
            else{
                //Writing recipient details to ClientList text file
                f.write(RecipientInfo);
                System.out.println("Added");
                //Closing the file
                f.close();
                
                //Generating the object for new Recipient
                Recipient r = generateObject(RecipientInfo);
                
                //Adding new Recipient to the Recipients Array
                Email_Client.RecipientsArray.add(r);
                
                
                String [] todayArray = today.split("/", -2);  //String array that contains year , month , date
                
                String[] birthdaydetails = r.getBirthday().split("/", -2); //String array that contains the recipients birthday
               
                //Checking whether the newly added recipient's birthday is today or not
                if(todayArray[1].trim().equals(birthdaydetails[1].trim()) && todayArray[2].trim().equals(birthdaydetails[2].trim())){
                    
                    sendGreetings(r);
                }
                
                
                
            }
            
        } catch (Exception e) {
            
        }
        
    }
    //--------------------------------------------------------------------------------------------------------------------
    
    public void sendGreetings(Recipient r){
        /*
        Method for Printing out the list of recipient having birthdays
        Here the birthday wish is sent for a given Recipient
        
        parameter :
            r --> Recipient Object
        
         /*
         Checking the object type.
         Beacuse only Personal and Official Friends have the data about birthdays
         */
         if(r instanceof Personal){
             
             //Generating birthday wish for Personal recipient
             String content = "Dear " + r.getName() + "," + "\n" +"hugs and love on your birthday" + " - Amila - ";               
             sendEmail(r.getEmail(), "Birthday Wish", content);
                
         }
         else if(r instanceof OfficialFriend){
             
             //Generating the birthday wish for OfficialFriend   
             String content = "Dear " + r.getName() + "," + "\n" +"Wish you a Happy Birthday" + " - Amila - ";
             sendEmail(r.getEmail(), "Birthday Wish", content);
                
         }
            
    }
    
    
    //---------------------------------------------------------------------------------------------------------------------
    
    
    public ArrayList<Recipient> createObjectsArray(){
        /*
        Generating an array to store all Recipient objects 
        in the application
        */
        //Array to store all recipient objects in the application
        ArrayList<Recipient> Recipients = new ArrayList<Recipient>();
        try {
            //File where the details of recipients are stored
            File fr = new File("C:\\Users\\Amila Kasun\\Desktop\\Edu\\UOM\\Academic\\Sem 2\\Program Construction\\Project Text Files\\ClientList.txt");
            if(fr.length() == 0){
                System.out.println("Client List is empty..");
            }
            else{
                Scanner scr = new Scanner(fr);
                while(scr.hasNextLine()){
                    Recipient r ;
                    String s = scr.nextLine();
                    //Generating an object from the given details
                    r = generateObject(s);
                    //Adding generated object to the Recipients Array list
                    Recipients.add(r);
                }
            }
 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Recipients;
        
    }
    //---------------------------------------------------------------------------------------------------------------
    
    public Recipient generateObject(String s){
        /*
        Method for genrating an Object according to the given details
        Here a given String is converting to a object (either for Personal, Official or Official Friend)
        
        parameters :
            s --> String contating details of recipient
        */
        
        String [] info = s.split(": ",-2);
        String rec = info[0];
        String [] det = info[1].split(",",-2);
            
        //When the oject is Personal
        if(rec.equals("Personal")){
                       
            Recipient r = new Personal(det[0],det[1],det[2],det[3]);
            return r;
        }
        //When the oject is Official
        else if(rec.equals("Official")){
            Recipient r = new Official(det[0],det[1],det[2]);
            return r;
        }
        //When the oject is OfficialFriend
        
        else{
            Recipient r = new OfficialFriend(det[0],det[1],det[2],det[3]);
            return r;
        }
       
        
        
        
        
       
    }
    //---------------------------------------------------------------------------------------------------------------------
    
    public void saveEmailObjects(ArrayList<Email> emailArray){
        /*
        Method for serializing an array of email objects
        Here Email objects in an Array are being serialized
        Parameters : 
            emailArray --> Array containing Email objects to be serialized
        */
        
        try {
            FileOutputStream f = new FileOutputStream("C:\\Users\\Amila Kasun\\Desktop\\Edu\\UOM\\Academic\\Sem 2\\Program Construction\\Project Text Files\\EmailObjects.ser");
            
            ObjectOutputStream out = new ObjectOutputStream(f);
            
            //Traversing throgh the array and saving email objects
            for(int i = 0 ; i < emailArray.size() ; i++){
                
                out.writeObject(emailArray.get(i));
            }
            
            
            out.close();
            f.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void Deserialize(){
        
        //ArrayList<Email> emailsArray = new ArrayList<Email>();
        
        try {
            FileInputStream filein = new FileInputStream("C:\\Users\\Amila Kasun\\Desktop\\Edu\\UOM\\Academic\\Sem 2\\Program Construction\\Project Text Files\\EmailObjects.ser");
            
            
            ObjectInputStream in = new ObjectInputStream(filein);
            
            Object object = null; //Creating a null object
            
            //Checking for object type (whether it is Email object)
            while((object = in.readObject()) instanceof Email){
                Email e;
                e = (Email)object;
                Email_Client.EmailArray.add(e);
            }
            
            in.close();
            filein.close();
            
            
        } catch (Exception e) {
            //Do nothing
        }
        
    }
    
    
    public boolean isInClientList(String ClientDetails){
        /*
        Method for checking whether a newly adding Recipient is already in the ClientList
        Parameters : 
            ClientDetails --> Details of newly adding recipient
        */
        try {
            File fr = new File("C:\\Users\\Amila Kasun\\Desktop\\Edu\\UOM\\Academic\\Sem 2\\Program Construction\\Project Text Files\\ClientList.txt");
            
            
            Scanner scr = new Scanner(fr);
            while(scr.hasNextLine()){
            
            String s = scr.nextLine();
            if(s.equals(ClientDetails)){
                return true;
            }
                   
        }
            
        } catch (Exception e) {
            
        }
        return false;
        
        
        
    }
    
   
}
