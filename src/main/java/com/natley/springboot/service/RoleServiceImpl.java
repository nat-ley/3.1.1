package com.natley.springboot.service;

import com.natley.springboot.dao.RoleRepository;
import com.natley.springboot.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role read(long id) {
        return roleRepository.getById(id);
    }

    @Override
    public void delete(long id) {
        roleRepository.delete(read(id));
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
