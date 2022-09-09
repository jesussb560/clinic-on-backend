package com.app.clinicon.prescription;

import java.util.List;

public interface PrescriptionService {
    
    List<Prescription> findAll();
    Prescription findById(long id);
    Prescription save(Prescription prescription);
    
}
