package Backend.SpringBootCrud.Controllers;

import Backend.SpringBootCrud.DTO.Users.GetUsersDto;
import Backend.SpringBootCrud.DTO.Users.UserLoginDTO;
import Backend.SpringBootCrud.Models.Users;
import Backend.SpringBootCrud.Repositries.UserRepositriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
//@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {

    @Autowired
    private UserRepositriy _userRepo;
    @PostMapping("/")
    public  String UserLogin(@RequestBody UserLoginDTO usr){
        return this._userRepo.UserLogin(usr);
    }
    @GetMapping("/")
    public List<GetUsersDto> GetAllUsers(){

        return this._userRepo.GetAllUsers();
    }
    @GetMapping("/getByid")
    public Users GetByIdUser(@RequestParam String Id){
        return  this._userRepo.GetByIdUser(Id);
    }
    @PostMapping("/getByEmail")
    public Users GetByEmail(@RequestParam String user_email){
        return this._userRepo.GetByEmail(user_email);
    }
}
