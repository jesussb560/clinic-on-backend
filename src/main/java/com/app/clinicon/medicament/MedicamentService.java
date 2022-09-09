package com.app.clinicon.medicament;

import java.util.List;

public interface MedicamentService {
    
    List<Medicament> findAll();
    Medicament findById(long id);
    Medicament save(Medicament medicament);
    
}
