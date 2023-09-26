package email_client;


public abstract class Recipient {
    private String name; //Name of the Recipient
    private String email;//Email Address of the Recipient
   
    
    public Recipient(String name,String email){
        this.name = name;
        this.email = email;
        
    }
    
    public String getName(){
        //getter method for name of the recipient
        return name;
    }
    
    public String getEmail(){
        //getter method for email of the recipient
        return email;
    }
    
    public String getBirthday(){
        return null;
    }
    
}
