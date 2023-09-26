
package email_client;


public class Personal extends Recipient{
    
    private String nickName;
    private String birthday;
    
    public Personal(String name,String nickName,String email,String birthday){
        super(name,email);
        this.birthday = birthday;
        this.nickName = nickName;
    }
    
    public String getNickName(){
        return nickName;
    }
    
    public String getBirthday(){
        return birthday;
    }
}
