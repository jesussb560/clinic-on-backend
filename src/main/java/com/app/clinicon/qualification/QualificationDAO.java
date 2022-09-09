package com.app.clinicon.qualification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QualificationDAO extends JpaRepository<Qualification, Long>, JpaSpecificationExecutor<Qualification> {
    
}
