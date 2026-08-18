package com.demo.first.App.service;


import com.demo.first.App.Model.User;
import com.demo.first.App.controller.UserController;
import com.demo.first.App.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private Map<Integer, User> userDb = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User createUser(User user) {
        logger.info("Creating user........info");
        logger.debug("Creting user  ....Debug");
        logger.trace("Creting user  ....trace");
        System.out.println(user.getEmail());
        userDb.putIfAbsent(user.getId(), user);
        return user;

    }

    public User updateuser(User user) {
        if (!userDb.containsKey(user.getId())) {
            logger.error("error when finding user with id {}", user.getId());
            throw new UserNotFoundException("user with id " + user.getId() + "does not exist");

        }
           userDb.put(user.getId(),user);
        return  user;
    }

    public boolean deleteUser(int id) {
        if(!userDb.containsKey(id))
            return false;

        userDb.remove(id);
        return true;
    }

    public List<User> getAllusers() {
         return new ArrayList<>(userDb.values());
    }

    public User getUserById(int id) {
        return  userDb.get(id);

    }

    public List<User> searchUsers(String name, String email) {


        return  userDb.values().stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .toList();

    }



}
