package com.app.clinicon.qualification;

import java.util.List;

public interface QualificationService {
    
    List<Qualification> findAll();
    Qualification findById(long id);
    Qualification save(Qualification qualification);
    
}
