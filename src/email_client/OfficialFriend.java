
package email_client;


 
public class OfficialFriend extends Official{
    private String birthday;
    
    public OfficialFriend(String Name,String email,String designation,String birthday){
        super(Name,email,designation);
        this.birthday = birthday;
        
    }
    
    public String getBirthday(){
        return birthday;
    }
        
}
