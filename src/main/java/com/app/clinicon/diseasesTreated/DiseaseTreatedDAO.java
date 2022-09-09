package com.app.clinicon.diseasesTreated;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DiseaseTreatedDAO extends JpaRepository<DiseaseTreated, Long>, JpaSpecificationExecutor<DiseaseTreated> {
    
}
