package com.umar.registerform.userbean.controller;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.umar.registerform.userbean.UserData;
import com.umar.registerform.userbean.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.umar.registerform.userbean.UserData;
import com.umar.registerform.userbean.service.UserDataService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.umar.registerform.userbean.UserData;
import com.umar.registerform.userbean.service.UserDataService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDataService userService;
    @PostMapping("/insert")
    public ResponseEntity<String> saveUserData(@RequestBody UserData userData) {
        try {
            // Check if the user already exists
            if (userService.isUserExists(userData.getEmail())) {
                return new ResponseEntity<>("User with this email already exists", HttpStatus.CONFLICT);
            }

            // Save the user data
            userService.saveUserData(userData);

            // Send email
            userService.sendEmail(userData.getEmail());

            return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error while saving user data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user-list")
    public ResponseEntity<List<UserData>> listUsers() {
        try {
            List<UserData> userList = userService.getAll();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user-delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>("Delete successful", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error while deleting user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/login/{email}")
    public ResponseEntity<Object> login(@PathVariable("email") String email) {
        try {
            UserData login = userService.login(email);
            return new ResponseEntity<>(login, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("user",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody UserData updatedUserData, @PathVariable("id") long id) {
        try {
            UserData updatedUser = userService.updateUser(id, updatedUserData);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
         catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            return new ResponseEntity<>("Error while updating user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
