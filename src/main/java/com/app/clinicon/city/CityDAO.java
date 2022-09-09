package com.app.clinicon.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CityDAO extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
    
}
