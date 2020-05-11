package com.bodify.bodify.controller;

import com.bodify.bodify.model.User;
import com.bodify.bodify.service.SequenceGenerator;
import com.bodify.bodify.service.UserManagementImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserManagementImpl UserManagementImpl;
    @Autowired
    private SequenceGenerator sequenceGenerator;


    //Test Endpoint
    @GetMapping(path = "/welcome")
    public String welcome() throws Exception {
        return "Test Endpoint";
    }

    //Add a new User document to Collection of Users
    @PostMapping(path = "/registerUser")
    public ResponseEntity registerUser(@RequestBody User user) throws Exception {
        HashMap<String, Object> resp = new HashMap<>();
        user.setId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));

        //Try adding user document to collection and catch validation exceptions
        try {
            UserManagementImpl.registerUser(user);
        } catch (ConstraintViolationException e) {
            HashMap<String, String> messages = new HashMap<>();
            e.getConstraintViolations().stream().forEach(constraintViolation -> {
                messages.put(
                        constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage());
            });
            resp.put("error", true);
            resp.put("messages", messages);
            resp.put("user", user);
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }
        resp.put("user", user);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    //Get User by their ID
    @GetMapping(path = "/getUser")
    public ResponseEntity getUserById(@RequestParam("id") Long id) throws Exception {
        User user = UserManagementImpl.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //Get all Users from Collection
    @GetMapping(path = "/getAllUsers")
    public ResponseEntity getAllUsers() throws Exception {
        List<User> allUsers = UserManagementImpl.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    //Update a specific User document
    @PutMapping(path = "/updateUser")
    public ResponseEntity updateUser(@RequestParam("id") Long id, @RequestBody User user) throws Exception {
        HashMap<String, Object> resp = new HashMap<>();
        UserManagementImpl.updateUser(user);
        resp.put("user", user);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    //Delete a User document
    @DeleteMapping(path = "/deleteUser")
    public ResponseEntity deleteUser(@RequestParam("id") Long id) throws Exception {
        UserManagementImpl.deleteUser(id);
        HashMap<String, String> resp = new HashMap<>();
        resp.put("message", "User successfully deleted");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }



}
