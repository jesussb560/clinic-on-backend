package com.app.clinicon.specialty;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyDAO specialtyDAO;

    @Override
    public List<Specialty> findAll() {
        return specialtyDAO.findAll();
    }

    @Override
    public Specialty findById(long id) {
        return specialtyDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("Specialty not found"));
    }

    @Override
    public Specialty save(Specialty specialty) {
        return specialtyDAO.save(specialty);
    }
    
}
