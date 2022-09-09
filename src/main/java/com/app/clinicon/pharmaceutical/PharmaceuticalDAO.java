package com.app.clinicon.pharmaceutical;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PharmaceuticalDAO extends JpaRepository<Pharmaceutical, Long>, JpaSpecificationExecutor<Pharmaceutical> {
    
}
