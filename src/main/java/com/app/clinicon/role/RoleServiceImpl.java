package com.app.clinicon.role;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService{

    private final RoleDAO roleDAO;

    @Override
    public List<Role> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public Role findById(long id) {
        return roleDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("Role not found"));
    }

    @Override
    public Role save(Role role) {
        return roleDAO.save(role);
    }
    
}
