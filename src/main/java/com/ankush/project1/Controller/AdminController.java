package com.ankush.project1.Controller;

import com.ankush.project1.Entity.User;
import com.ankush.project1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping("all-user")
    public ResponseEntity<?> getAllUser(){
       List<User> all= userService.getAll();
       if(all != null && !all.isEmpty())
       {
           return new ResponseEntity<>(all, HttpStatus.OK);
       }
       return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin")
    public void createAdmin(@RequestBody User user){
        userService.saveAdmin(user);
    }
}