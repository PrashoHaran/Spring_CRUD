package com.example.spring_boot_demo.controllers;

import com.example.spring_boot_demo.entity.UserEntity;
import com.example.spring_boot_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // Update User API
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity userDetails) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            // Add other fields you want to update

            UserEntity updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete User API
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
