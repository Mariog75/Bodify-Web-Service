package com.bodify.bodify.controller;

import com.bodify.bodify.model.User;
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
    private UserManagementImpl registrationImpl;


    @GetMapping(path = "/welcome")
    public String welcome() throws Exception {
        return "Test Endpoint";
    }

    @PostMapping(path = "/registerUser")
    public ResponseEntity registerUser(@RequestBody User user) throws Exception {
        HashMap<String, Object> resp = new HashMap<>();
        try {
            registrationImpl.registerUser(user);
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

    @GetMapping(path = "/getUser")
    public ResponseEntity getUserById(@RequestParam("id") String id) throws Exception {
        User user = registrationImpl.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllUsers")
    public ResponseEntity getAllUsers() throws Exception {
        List<User> allUsers = registrationImpl.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PutMapping(path = "/updateUser")
    public ResponseEntity updateUser(@RequestParam("id") String id, @RequestBody User user) throws Exception {
        HashMap<String, Object> resp = new HashMap<>();
        registrationImpl.updateUser(user);
        resp.put("user", user);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteUser")
    public ResponseEntity deleteUser(@RequestParam("id") String id) throws Exception {
        registrationImpl.deleteUser(id);
        HashMap<String, String> resp = new HashMap<>();
        resp.put("message", "User successfully deleted");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }



}
