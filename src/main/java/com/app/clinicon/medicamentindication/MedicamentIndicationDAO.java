package com.app.clinicon.medicamentindication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MedicamentIndicationDAO extends JpaRepository<MedicamentIndication, Long>, JpaSpecificationExecutor<MedicamentIndication> {
    
}
