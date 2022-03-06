package com.natley.springboot.service;

import com.natley.springboot.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();

    Role read(long id);

    void delete(long id);

    void save(Role role);

    Role findByName(String name);

}
