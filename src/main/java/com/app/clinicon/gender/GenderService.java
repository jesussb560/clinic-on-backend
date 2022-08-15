package com.app.clinicon.gender;

import java.util.List;

public interface GenderService {

    List<Gender> findAll();
    Gender findById(long id);
    Gender save(Gender gender);
    
}
