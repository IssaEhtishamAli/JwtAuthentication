package Backend.SpringBootCrud.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Departments")
public class Departments {

    @Id
    private ObjectId _id;
    private String dept_name;

    public Departments() {
    }

    public Departments(ObjectId _id, String dept_name) {
        this._id = _id;
        dept_name = dept_name;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return dept_name;
    }

    public void setName(String dept_name) {
        this.dept_name = dept_name;
    }
}
