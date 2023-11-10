package Backend.SpringBootCrud.Controllers;

import Backend.SpringBootCrud.DTO.Employees.GetEmployeeDTO;
import Backend.SpringBootCrud.DTO.Employees.UpdateEmployeeDTO;
import Backend.SpringBootCrud.Models.Employees;
import Backend.SpringBootCrud.Repositries.EmployeesRepositriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/employees")
//@CrossOrigin(origins = "http://localhost:4200")
public class EmployeesController {
    @Autowired
    private EmployeesRepositriy _empRepo;

    @PostMapping("/")
    public Employees save(@RequestBody Employees emp){

        return _empRepo.createEmployee(emp);
    }
    @GetMapping("/")
    public List<GetEmployeeDTO> getAllEmployees(){

        return _empRepo.getAllEmployees();
    }
    @PutMapping("/")
    public Employees update(@RequestBody UpdateEmployeeDTO emp){
       return  _empRepo.updateEmployee(emp);
    }
    @DeleteMapping("/")
    public void delete(@RequestParam String Id){
        _empRepo.deleteEmployee(Id);
    }
}
