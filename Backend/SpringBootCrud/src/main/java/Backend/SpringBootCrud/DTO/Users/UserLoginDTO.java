package Backend.SpringBootCrud.DTO.Users;

public class UserLoginDTO {
    public  String user_email;
    public  String user_password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String user_email, String user_password) {
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
