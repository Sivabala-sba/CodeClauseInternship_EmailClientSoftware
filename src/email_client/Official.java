
package email_client;


public class Official extends Recipient{
    private String designation;
    
    public Official(String Name,String email,String designation){
        super(Name,email);
        this.designation = designation;
    }
    
    public String getDesignation(){
        return designation;
    }
    
    public String getBirthday(){
        return null;
    } 
    
}
