package Backend.SpringBootCrud.Models;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class Users {
    @Id
    private ObjectId _id;
    private String user_email;
    private  String user_password;

    public Users() {
    }

    public Users(ObjectId _id, String user_email, String user_password) {
        this._id = _id;
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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
