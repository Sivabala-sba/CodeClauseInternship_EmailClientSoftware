
package email_client;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Email_Client {

    public static ArrayList<Recipient> RecipientsArray;
    public static ArrayList<Email> EmailArray = new ArrayList<Email>();
    public static ArrayList<Recipient> RecipientsWithBirthday;
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Object containing Main Operation
        MainOperartions mo = new MainOperartions();
        
        try {
            
            //Array to store all the recipients
            RecipientsArray = mo.createObjectsArray();
            
            //Deserializing saved email objects and adding those into the EmailArray
            mo.Deserialize();
            
            //Process of sending emails when the programe starts
            
            //Geitting the date in string format
            Date thisDate = new Date();
            SimpleDateFormat date = new SimpleDateFormat("YYYY/MM/dd");
            String today = date.format(thisDate);
            
            //Array containg the recpients who have birthday
            RecipientsWithBirthday = mo.whoHasBirthdays(today);
           
            /*
            This is the logic for checking whether a birthday wish has been already sent to a 
            recipient. As the application may be opened several times a day , a wish could be sent
            twice for a given client. This logic avoids that 
            */
            for(int i = 0 ; i < RecipientsWithBirthday.size() ; i++){
                //Sending birthday greetings
                boolean check = true;
                Recipient r = RecipientsWithBirthday.get(i);
                for(int j = 0 ; j < EmailArray.size() ; j++){
                    Email e = EmailArray.get(j);
                    if(e.getEmailAddress().equals(r.getEmail()) && e.getSubject().equals("Birthday Wish")){
                        
                        check = false;
                        break;        
                    }
                }
                if(check){
                    mo.sendGreetings(r);
                }
                
                      
                
            }
            
            
        
        } catch (ArrayIndexOutOfBoundsException e) {
            //
        }
        
        
        
        //Getting inputs for the operations
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter option type: \n"
                  + "1 - Adding a new recipient\n"
                  + "2 - Sending an email\n"
                  + "3 - Printing out all the recipients who have birthdays\n"
                  + "4 - Printing out details of all the emails sent\n"
                  + "5 - Printing out the number of recipient objects in the application");

        int option = scanner.nextInt();
            
            

        switch(option){
            case 1:
                //Adding a new recipient
                      
                // input format - Official: nimal,nimal@gmail.com,ceo
                System.out.println("Add the recipients according to the following format. \n" +
                        "For official recipients -->  Official: <name>,<email>,<designation> \n"
                        + "For personal recipients -->  Personal: <name>,<nickname>,<email>,<birthday>\n"
                        + "For official_firend recipients -->  Office_friend: <name>,<email>,<designation>,<birthday>");
                
                Scanner Obj = new Scanner(System.in);
                // Use a single input to get all the details of a recipient
                String details = Obj.nextLine() + "\n";
                      
                // code to add a new recipient
                // store details in clientList.txt file
                mo.addRecipient(details);
                      
                
                break;
                      
            case 2:
                //Sending an email
                      
                //ArrayList<String> emailInfo = new ArrayList<String>();
                      
                // input format - email, subject, content
                System.out.println("Write the email according to the followeing format\n"
                        + "<email>,<subject>,<content>");
 
                Scanner Obj2 = new Scanner(System.in);
                String info = Obj2.nextLine();
                  
                //Storing email , subjcet , content in emailInfo array
                String [] emailInfo = info.split(",",-2);
                      
                      
                String emailAddress = emailInfo[0];
                String subject = emailInfo[1];
                String content = emailInfo[2];
                      
                
                // code to send an email 
                mo.sendEmail(emailAddress, subject,content);
                
                break;
            case 3:
                //Printing out all the recipients who have birthdays
                      
                // input format - yyyy/MM/dd (ex: 2018/09/17)
                System.out.println("Type the birthday in YYYY/MM/DD format");
                
                Scanner obj = new Scanner(System.in);
                String birthday = obj.nextLine(); 
                // code to print recipients who have birthdays on the given date
                ArrayList<Recipient> birthdayRecipients = mo.whoHasBirthdays(birthday);
                
                System.out.println("Today following Recipients have birthdays");
                for(int i = 0 ; i < birthdayRecipients.size() ; i++){
                    System.out.println(birthdayRecipients.get(i).getName());
                }
                
                break;
            case 4:
                //Printing out details of all the emails sent
                      
                // input format - yyyy/MM/dd (ex: 2018/09/17)
                //System.out.println(EmailArray.size());
                Scanner sc = new Scanner(System.in);
                String SentDate = sc.nextLine();
                
                for(int i = 0 ; i < EmailArray.size() ; i++){
                    
                    if(EmailArray.get(i).getDateOfSendingMail().equals(SentDate)){
                        Email e = EmailArray.get(i);
                        System.out.println("To : " + e.getEmailAddress() + "\n"
                                + "Subject : " + e.getSubject() + "\n"
                                        + "Content : " + e.getContent() + "\n"
                                                + "-----------------------------------------------------------------" + "\n");
                    }
                }
                
                      
                // code to print the details of all the emails sent on the input date
                break;
            case 5:
                //Printing out the number of recipient objects in the application
                // code to print the number of recipient objects in the application
                int recipientAmount = RecipientsArray.size();
                System.out.println("Number of recipient objects : " + recipientAmount);
                break;

        }
        
        mo.saveEmailObjects(EmailArray);

        // start email client
        // code to create objects for each recipient in clientList.txt
        // use necessary variables, methods and classes

    }
    
}

    
    

