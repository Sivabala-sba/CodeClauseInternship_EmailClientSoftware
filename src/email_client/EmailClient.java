package email_client;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class EmailClient {
    public void sendEmail(String emailAddress, String subject,String content){
        Email e = new Email(emailAddress,subject,content);
        String toAddress = e.getEmailAddress();
        String Subject = e.getSubject();
        String Content = e.getContent();
        SendEmailTLS sml = new SendEmailTLS();
        sml.sendMail(toAddress,Subject,Content);
        
        
    }
    public void sendGreeting(){
    }
    public void addRecipient(){
        Scanner Obj = new Scanner(System.in);
        String details = Obj.nextLine() + "\n";
                      
        try {
            FileWriter f = new FileWriter("E:\\EmailClientSoftware\\DataSample\\clientList.txt");
            f.write(details);
            f.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    public void whoHasBdays(){
    }
    public ArrayList<Recipient> createObjects(){
        ArrayList<Recipient> Recipients = new ArrayList<Recipient>();
        try {
            File fr = new File("E:\\EmailClientSoftware\\DataSample\\new1.txt");
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
