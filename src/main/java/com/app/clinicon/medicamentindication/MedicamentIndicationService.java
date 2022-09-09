package com.app.clinicon.medicamentindication;

import java.util.List;

public interface MedicamentIndicationService {

    List<MedicamentIndication> findAll();
    MedicamentIndication findById(long id);
    MedicamentIndication save(MedicamentIndication medicamentIndication);
    
}
