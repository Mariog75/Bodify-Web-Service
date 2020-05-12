package com.bodify.bodify.service;

import com.bodify.bodify.model.User;
import com.bodify.bodify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManagementImpl implements UserManagement<String, User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) throws Exception {
        user = userRepository.save(user);
        return user;
    }

    @Override
    public User getUserById(String userId) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        } else {
            throw new Exception("No user found");
        }
    }

    public List<User> getAllUsers() throws Exception {
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }

    @Override
    public User updateUser(User user) throws Exception {
        user = userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        if(userId == null) {
            throw new Exception("User is null");
        } else {
            userRepository.deleteById(userId);
        }
    }
}
