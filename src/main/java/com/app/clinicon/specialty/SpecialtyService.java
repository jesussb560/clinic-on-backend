package com.app.clinicon.specialty;

import java.util.List;

public interface SpecialtyService {

    List<Specialty> findAll();
    Specialty findById(long id);
    Specialty save(Specialty specialty);
    
}
