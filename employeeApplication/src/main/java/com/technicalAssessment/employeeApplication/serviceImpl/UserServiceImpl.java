package com.technicalAssessment.employeeApplication.serviceImpl;
import com.technicalAssessment.employeeApplication.model.User;
import com.technicalAssessment.employeeApplication.repository.UserRepo;
import com.technicalAssessment.employeeApplication.service.UserService;
import com.technicalAssessment.employeeApplication.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtUtils jwtUtils;
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) throws IllegalStateException{
        Optional<User> optionalUser = userRepo.findById(user.getUserName());
        if ((optionalUser.isEmpty()) && (user.getUserName()!=null)) {
            user.setUserId(userRepo.count() + 1);
            passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = this.passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            return userRepo.save(user);
        }else {
            throw new IllegalStateException("user name is taken, try a different name");
        }
    }
    @Override
    public Object loginUser(User user) throws IllegalStateException{
        String response;
       passwordEncoder = new BCryptPasswordEncoder();
        Optional<User> optionalUser = userRepo.findById(user.getUserName());
        if (optionalUser.isPresent()){
            User userDb = optionalUser.get();
            if(passwordEncoder.matches(user.getPassword(), userDb.getPassword())){
                String token = jwtUtils.generateJwt(userDb);
                response = token;
                return response;

           }else {
          throw new IllegalStateException("password mismatched");

           }
       } else {
           throw new IllegalStateException("User not registered, please register");
       }



    }
}
