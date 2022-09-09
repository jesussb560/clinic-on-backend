package com.app.clinicon.diseasesTreated;

import java.util.List;

public interface DiseaseTreatedService {

    List<DiseaseTreated> findAll();
    DiseaseTreated findById(long id);
    DiseaseTreated save(DiseaseTreated diseaseTreated);
    
}
