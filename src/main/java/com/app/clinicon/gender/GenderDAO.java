package com.app.clinicon.gender;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderDAO extends JpaRepository<Gender, Long> {
    
}
