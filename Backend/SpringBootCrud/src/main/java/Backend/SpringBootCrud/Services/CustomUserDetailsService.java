package Backend.SpringBootCrud.Services;

import Backend.SpringBootCrud.Models.Users;
import Backend.SpringBootCrud.Repositries.UserRepositriy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepositriy usersRepositiry;

    public CustomUserDetailsService(UserRepositriy usersRepositiry) {
        this.usersRepositiry = usersRepositiry;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepositiry.GetByEmail(email);
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUser_email())
                        .password(user.getUser_password())
                        .roles(roles.toArray(new String[0]))
                        .build();
        return userDetails;
    }
}