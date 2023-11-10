package Backend.SpringBootCrud.Controllers;
import Backend.SpringBootCrud.DTO.Departments.CreateDepartmentDTO;
import Backend.SpringBootCrud.DTO.Departments.GetDepartmentDTO;
import Backend.SpringBootCrud.DTO.Departments.UpdateDepartmentDTO;
import Backend.SpringBootCrud.Models.Departments;
import Backend.SpringBootCrud.Repositries.DepartmentsRepositriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
//@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentController {
    @Autowired
    private DepartmentsRepositriy _deptRepo;
    @PostMapping("/")
    public Departments save(@RequestBody Departments dept){
        return _deptRepo.createDepartment(dept);
    }
    @GetMapping("/")
    public List<GetDepartmentDTO> getAllDepartments(){

        return _deptRepo.getDepartments();
    }
    @PutMapping("/")
    public Departments update(@RequestBody Departments dept){
        return _deptRepo.updateDepartment(dept);
    }
    @DeleteMapping("/")
    public String delete(@RequestParam String Id){
        _deptRepo.deleteDepartment(Id);
        return "Department delete successfully";
    }
}
