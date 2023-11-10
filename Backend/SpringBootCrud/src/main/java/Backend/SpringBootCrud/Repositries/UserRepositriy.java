package Backend.SpringBootCrud.Repositries;

import Backend.SpringBootCrud.DTO.Users.GetUsersDto;
import Backend.SpringBootCrud.DTO.Users.UserLoginDTO;
import Backend.SpringBootCrud.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositriy {

    @Autowired
    private MongoTemplate mongoTemplate;

    public  Users UserRegisteration(Users usr){
        Users user = new Users();
        user.setUser_email(usr.getUser_email());
        user.setUser_password(usr.getUser_password());
        Users result = mongoTemplate.insert(user);
        return result;
    }
    public Users GetByEmail(String user_email){
        Query query = new Query();
        query.addCriteria(Criteria.where("user_email").is(user_email));
        Users user = mongoTemplate.findOne(query,Users.class);
        return  user;
    }
    public  String UserLogin(UserLoginDTO usr){
        Query query = new Query();
        query.addCriteria(Criteria.where("user_email").is(usr.getUser_email()));
        Users user = mongoTemplate.findOne(query,Users.class);
        if(user.getUser_email().equals(usr.getUser_email())){
            return "user login succesfully";
        }
        else{
            return "user does not exsist";
        }
    }
    public  List<GetUsersDto> GetAllUsers(){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("user_email","user_password","_id")
        );
        List<GetUsersDto> results = mongoTemplate.aggregate(aggregation, "Users", GetUsersDto.class).getMappedResults();
        return results;
    }


    public  Users GetByIdUser(String Id){
        return  mongoTemplate.findById(Id, Users.class);
    }
}
