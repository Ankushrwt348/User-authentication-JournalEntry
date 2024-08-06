package com.ankush.project1.Controller;

import com.ankush.project1.Entity.User;
import com.ankush.project1.Repo.UserRepo;
import com.ankush.project1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @PutMapping
    public User updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User Username = userService.findByUsername(username);
        Username.setUsername(user.getUsername());
        Username.setPassword(user.getPassword());
        userService.saveNewUser(Username);
       return Username;
    }
    @DeleteMapping
    public boolean deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userRepo.deleteByUsername(username);
        return true;
    }

}
