package com.app.clinicon.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.app.clinicon.role.RoleName;

public interface UserDAO extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{

    User findByEmailAddress(String emailAddress);
    User findByRut(String rut);
    boolean existsByEmailAddress(String emailAddress);
    boolean existsByRut(String rut);
    long countByRoles_Name(RoleName roleName);
    
    
    
}
