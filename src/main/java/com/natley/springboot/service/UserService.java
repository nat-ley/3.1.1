package com.natley.springboot.service;

import com.natley.springboot.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User readUser(long id);

    void deleteUser(long id);

    void save(User user);
}
