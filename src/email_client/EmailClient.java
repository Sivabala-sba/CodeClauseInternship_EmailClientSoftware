
package email_client;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class EmailClient {
    public void sendEmail(String emailAddress, String subject,String content){
        //Send a e-mail
        
        //Creating new email boject
        Email e = new Email(emailAddress,subject,content);
        
        //E-mail adddress of the recieveer
        String toAddress = e.getEmailAddress();
        
        //Subject of the E-mail
        String Subject = e.getSubject();
        
        //Content of the E-mail
        String Content = e.getContent();
        
        //Code for sending the email
        
        //Creating SendEmailTLS object
        SendEmailTLS sml = new SendEmailTLS();
        sml.sendMail(toAddress,Subject,Content);
        
        
    }
    public void sendGreeting(){
        //send tghe birthday greeting
    }
    public void addRecipient(){
        //Add recipient to the text file
        // input format - Official: nimal,nimal@gmail.com,ceo
        Scanner Obj = new Scanner(System.in);
        String details = Obj.nextLine() + "\n";
                      
        try {
            //Accessing ClientList text file
            FileWriter f = new FileWriter("C:\\Users\\Amila Kasun\\Desktop\\Edu\\UOM\\Academic\\Sem 2\\Program Construction\\Project\\clientList.txt");
            //Writing recipient details to ClientList text file
            f.write(details);
            //Closing the file
            f.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    public void whoHasBdays(){
        //Print the list of recipient having birthdays
    }
    public ArrayList<Recipient> createObjects(){
        ArrayList<Recipient> Recipients = new ArrayList<Recipient>();
        try {
            File fr = new File("C:\\Users\\Amila Kasun\\Desktop\\Edu\\UOM\\Academic\\Sem 2\\Program Construction\\File handling\\new1.txt");
            Scanner scr = new Scanner(fr);
            while(scr.hasNextLine()){
                Recipient r ;
                String s = scr.nextLine();
                String [] info = s.split(": ",-2);
                String rec = info[0];
                String [] det = info[1].split(",",-2);
                if(rec.equals("Personal")){
                       
                    r = new Personal(det[0],det[1],det[2],det[3]);
                    Recipients.add(r);
                        
                }
                else if(rec.equals("Official")){
                    r = new Official(det[0],det[1],det[2]);
                    Recipients.add(r);
                }
                else{
                    r = new OfficialFriend(det[0],det[1],det[2],det[3]);
                    Recipients.add(r);
                }
            }
        } catch (Exception e) {
         
        }
        return Recipients;
        
    }
   
}
