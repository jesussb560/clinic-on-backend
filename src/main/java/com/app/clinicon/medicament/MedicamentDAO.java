package com.app.clinicon.medicament;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MedicamentDAO extends JpaRepository<Medicament, Long>, JpaSpecificationExecutor<Medicament> {
    
}
