package com.app.clinicon.medicament;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MedicamentServiceImpl implements MedicamentService {
    
    private final MedicamentDAO medicamentDAO;

    @Override
    public List<Medicament> findAll() {
        return medicamentDAO.findAll();
    }

    @Override
    public Medicament findById(long id) {
        return medicamentDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("Medicament not found"));
    }

    @Override
    public Medicament save(Medicament medicament) {
        return medicamentDAO.save(medicament);
    }
    
}
