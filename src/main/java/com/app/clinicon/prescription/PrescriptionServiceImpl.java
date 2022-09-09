package com.app.clinicon.prescription;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionDAO prescriptionDAO;

    @Override
    public List<Prescription> findAll() {
        return prescriptionDAO.findAll();
    }

    @Override
    public Prescription findById(long id) {
        return prescriptionDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("Prescription not found"));
    }

    @Override
    public Prescription save(Prescription prescription) {
        return prescriptionDAO.save(prescription);
    }
    
}
