package com.ankush.project1.Controller;

import com.ankush.project1.Entity.User;
import com.ankush.project1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private  UserService userService;

    @GetMapping("test")
    public String test() {
        return "ok";
    }

    @PostMapping("create-user")
    public User createUser(@RequestBody User user) {
        userService.saveNewUser(user);
        return user;
    }

}
