package com.app.clinicon.prescription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrescriptionDAO extends JpaRepository<Prescription, Long>, JpaSpecificationExecutor<Prescription> {
    
}
