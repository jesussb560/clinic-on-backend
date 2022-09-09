package com.app.clinicon.medicamentindication;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MedicamentIndicationServiceImpl implements MedicamentIndicationService{
    
    private final MedicamentIndicationDAO medicamentIndicationDAO;
    
    @Override
    public List<MedicamentIndication> findAll() {
        return medicamentIndicationDAO.findAll();
    }

    @Override
    public MedicamentIndication findById(long id) {
        return medicamentIndicationDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("Medicament indication not found"));
    }

    @Override
    public MedicamentIndication save(MedicamentIndication medicamentIndication) {
        return medicamentIndicationDAO.save(medicamentIndication);
    }
    
}
