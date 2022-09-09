package com.app.clinicon.qualification;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class QualificationServiceImpl implements QualificationService {

    private final QualificationDAO qualificationDAO;

    @Override
    public List<Qualification> findAll() {
        return qualificationDAO.findAll();
    }

    @Override
    public Qualification findById(long id) {
        return qualificationDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("Qualification not found"));
    }

    @Override
    public Qualification save(Qualification qualification) {
        return qualificationDAO.save(qualification);
    }
    
}
