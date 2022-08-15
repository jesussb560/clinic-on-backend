package com.app.clinicon.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long>{

    User findByEmailAddress(String emailAddress);
    User findByRut(String rut);
    boolean existsByEmailAddress(String emailAddress);
    boolean existsByRut(String rut);
    
}
