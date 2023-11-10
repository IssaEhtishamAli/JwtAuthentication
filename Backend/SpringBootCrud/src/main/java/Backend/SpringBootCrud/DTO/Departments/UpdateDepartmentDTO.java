package Backend.SpringBootCrud.DTO.Departments;

import org.bson.types.ObjectId;

public class UpdateDepartmentDTO {
    private ObjectId _id;
    private String dept_name;

    public UpdateDepartmentDTO() {
    }
    public UpdateDepartmentDTO(ObjectId _id, String dept_name) {
        _id = _id;
        dept_name = dept_name;
    }

    public ObjectId getDept_id() {
        return _id;
    }

    public void setDept_id(ObjectId _id) {
        _id = _id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        dept_name = dept_name;
    }
}
