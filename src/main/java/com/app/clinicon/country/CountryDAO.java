package com.app.clinicon.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CountryDAO extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {
    
}
