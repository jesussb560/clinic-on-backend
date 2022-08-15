package com.app.clinicon.gender;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class GenderServiceImpl implements GenderService {

    private final GenderDAO genderDAO;

    @Override
    public List<Gender> findAll() {
        return genderDAO.findAll();
    }

    @Override
    public Gender findById(long id) {
        return genderDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("Gender not found"));
    }

    @Override
    public Gender save(Gender gender) {
        return genderDAO.save(gender);
    }
    
}
