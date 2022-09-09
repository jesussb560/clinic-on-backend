package com.app.clinicon.pharmaceutical;

import java.util.List;

public interface PharmaceuticalService {
    
    List<Pharmaceutical> findAll();
    Pharmaceutical findById(long id);
    Pharmaceutical save(Pharmaceutical pharmaceutical);

}
