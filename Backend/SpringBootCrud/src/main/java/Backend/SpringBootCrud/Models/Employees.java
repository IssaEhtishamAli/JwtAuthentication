package Backend.SpringBootCrud.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Employees")
public class Employees {
    @Id
    private ObjectId _id;
    private ObjectId dept_id;
    private String emp_name;
    private String emp_contactno;
    private String emp_age;
    private String emp_email;
    public Employees() {
    }
    public Employees(ObjectId _id, ObjectId dept_id, String emp_name, String emp_contactno, String emp_age, String emp_email) {
        this._id = _id;
        this.dept_id = dept_id;
        this.emp_name = emp_name;
        this.emp_contactno = emp_contactno;
        this.emp_age = emp_age;
        this.emp_email = emp_email;
    }

    public ObjectId getdept_id() {
        return dept_id;
    }

    public void setdept_id(ObjectId dept_id) {
        this.dept_id = dept_id;
    }

    public String getemp_name() {
        return emp_name;
    }

    public void setemp_name(String emp_ame) {
        this.emp_name = emp_ame;
    }

    public String getemp_contactno() {
        return emp_contactno;
    }

    public void setemp_contactno(String emp_contactno) {
        this.emp_contactno = emp_contactno;
    }

    public String getemp_age() {
        return emp_age;
    }

    public void setemp_age(String emp_age) {
        this.emp_age = emp_age;
    }

    public String getemp_email() {
        return emp_email;
    }

    public void setemp_email(String emp_email) {
        this.emp_email = emp_email;
    }
}
