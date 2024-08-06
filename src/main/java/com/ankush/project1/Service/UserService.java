package com.ankush.project1.Service;

import com.ankush.project1.Entity.User;
import com.ankush.project1.Repo.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    public final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepo.save(user);
    }
    public void saveOldUser(User user){
        userRepo.save(user);
    }
    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepo.save(user);
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public Optional<User> getById(ObjectId id){
        return userRepo.findById(id);
    }
    public void deleteById(ObjectId id){
        userRepo.deleteById(id);
    }
    public void deleteAll(){}

    public User findByUsername(String username){
       return userRepo.findByUsername(username);
    }

}
