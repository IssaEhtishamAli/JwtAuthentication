package Backend.SpringBootCrud.Repositries;

import Backend.SpringBootCrud.DTO.Departments.CreateDepartmentDTO;
import Backend.SpringBootCrud.DTO.Departments.GetDepartmentDTO;
import Backend.SpringBootCrud.DTO.Departments.UpdateDepartmentDTO;
import Backend.SpringBootCrud.DTO.Employees.GetEmployeeDTO;
import Backend.SpringBootCrud.Models.Departments;
import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentsRepositriy{
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<GetDepartmentDTO> getDepartments(){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("dept_name").and("$Departments._id.$oid").as("dept_id")
        );
        List<GetDepartmentDTO> results = mongoTemplate.aggregate(aggregation, "Departments", GetDepartmentDTO.class).getMappedResults();
        return results;
    }
    public Departments updateDepartment(Departments dept){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(dept.get_id()));
        Departments depts = mongoTemplate.findOne(query,Departments.class);
        depts.setName(dept.getName());
        return  mongoTemplate.save(depts);
    }
    public Departments createDepartment(Departments dept){
        Departments depts = new Departments();
        depts.setName(dept.getName());
        return  mongoTemplate.insert(depts);
    }
    public void deleteDepartment(String  Id){
        Query query =new Query(Criteria.where("_id").is(new ObjectId(Id)));
        DeleteResult deleteEmployee = mongoTemplate.remove(query,"Departments");
        deleteEmployee.getDeletedCount();
    }}
