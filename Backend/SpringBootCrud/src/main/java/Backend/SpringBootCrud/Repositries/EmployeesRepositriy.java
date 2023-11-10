package Backend.SpringBootCrud.Repositries;
import Backend.SpringBootCrud.DTO.Employees.GetEmployeeDTO;
import Backend.SpringBootCrud.DTO.Employees.UpdateEmployeeDTO;
import Backend.SpringBootCrud.Models.Employees;
import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EmployeesRepositriy {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<GetEmployeeDTO> getAllEmployees(){
        LookupOperation lookup = LookupOperation.newLookup().
                from("Departments").
                localField("dept_id").
                foreignField("_id").
                as("Departments");
        Aggregation aggregation = Aggregation.newAggregation(
                lookup,
                Aggregation.unwind("Departments"),
                Aggregation.project("emp_name","dept_id","emp_contactno","emp_age","emp_email").and("$Departments.dept_name").as("dept_name")
        );
        List<GetEmployeeDTO> results = mongoTemplate.aggregate(aggregation, "Employees", GetEmployeeDTO.class).getMappedResults();
        return results;
    }
    public Employees updateEmployee(UpdateEmployeeDTO emp){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(emp.get_id()));
        Employees emps = mongoTemplate.findOne(query,Employees.class);
        emps.setemp_name(emp.getemp_name());
        emps.setemp_age(emp.getemp_age());
        emps.setdept_id(emp.getdept_id());
        emps.setemp_contactno(emp.getemp_contactno());
        emps.setemp_email(emp.getemp_email());
        return mongoTemplate.save(emps);
    }
    public Employees createEmployee(Employees emp){
        Employees emps = new Employees();
            emps.setemp_name(emp.getemp_name());
            emps.setemp_age(emp.getemp_age());
            emps.setdept_id(emp.getdept_id());
            emps.setemp_contactno(emp.getemp_contactno());
            emps.setemp_email(emp.getemp_email());
            return mongoTemplate.insert(emps);

    }
    public void deleteEmployee(String Id){
        Query query =new Query(Criteria.where("_id").is(new ObjectId(Id)));
        DeleteResult deleteEmployee = mongoTemplate.remove(query,"Employees");
        deleteEmployee.getDeletedCount();
    }
}
