package com.techelevator.controller;

import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.User;
import com.techelevator.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class UserController {

    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> allUsers = new ArrayList<>();
        try{
            allUsers = userService.getUsers();
            if (allUsers == null) {
                throw new ServiceException("Users not found.");
            } else {
                return allUsers;
            }
        } catch (DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }


}
