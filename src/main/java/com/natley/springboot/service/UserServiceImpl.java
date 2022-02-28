package com.natley.springboot.service;

import com.natley.springboot.dao.UserRepository;
import com.natley.springboot.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User readUser(long id) {
        return userRepository.getById(id);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.delete(readUser(id));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}

