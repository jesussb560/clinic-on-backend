package com.app.clinicon.pharmaceutical;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PharmaceuticalServiceImpl implements PharmaceuticalService{
    
    private final PharmaceuticalDAO pharmaceuticalDAO;

    @Override
    public List<Pharmaceutical> findAll() {
        return pharmaceuticalDAO.findAll();
    }

    @Override
    public Pharmaceutical findById(long id) {
        return pharmaceuticalDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("Pharmaceutical not found"));
    }

    @Override
    public Pharmaceutical save(Pharmaceutical pharmaceutical) {
        return pharmaceuticalDAO.save(pharmaceutical);
    }
    
}
