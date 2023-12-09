package com.techelevator.service;

import com.techelevator.dao.website.*;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.ServiceException;
import com.techelevator.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private UserDao userDao;


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers() {
        try{
            List<User> users = userDao.getUsers();
            if (users == null) {
                throw new ServiceException("User list not found.");
            } else {
                return users;
            }
        } catch(DaoException e) {
            throw new ServiceException("An error has occurred: " + e.getMessage());
        }
    }



}

