package com.example.spring_boot_demo.controllers;

import com.example.spring_boot_demo.entity.UserEntity;
import com.example.spring_boot_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    //@GetMapping
    //public String getUsers(){
     //   return "Hello API";
    //}

    //Get User List API
    @GetMapping //
    public List<UserEntity> getUsers(){

        //return Arrays.asList(new User(1L,"John", "john@gmail.com"),new User(2L,"Joe", "joe@gmail.com"),new User(3L,"Alex", "Alex@gmail.com"));

        return userRepository.findAll();
    }

    //Send User Details
    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) {
        //System.out.println("User Data:"+user.getName()+","+user.getEmail());
        return userRepository.save(user);
    }

    //Get Single User API
    @GetMapping("/{id}")
    public Optional<UserEntity> getUserById(@PathVariable Long id){
        return userRepository.findById(id);
    }

    //Update User API
    @PutMapping("/{id}")
    public Optional<UserEntity> updateUser (@PathVariable Long id, @RequestBody UserEntity user) {
        return userRepository.findById(id);

    }


}
