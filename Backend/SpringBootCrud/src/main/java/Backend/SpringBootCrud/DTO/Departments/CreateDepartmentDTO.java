package Backend.SpringBootCrud.DTO.Departments;

import org.bson.types.ObjectId;

public class CreateDepartmentDTO {
    private String dept_name;

    public CreateDepartmentDTO() {
    }
    public CreateDepartmentDTO(String dept_name) {
        dept_name = dept_name;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        dept_name = dept_name;
    }
}
