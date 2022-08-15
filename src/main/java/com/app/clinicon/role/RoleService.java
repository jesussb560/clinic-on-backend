package com.app.clinicon.role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();
    Role findById(long id);
    Role save(Role role);
    
}
