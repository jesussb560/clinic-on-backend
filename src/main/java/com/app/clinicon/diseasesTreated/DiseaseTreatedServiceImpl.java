package com.app.clinicon.diseasesTreated;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DiseaseTreatedServiceImpl implements DiseaseTreatedService {

    private final DiseaseTreatedDAO diseaseTreatedDAO;

    @Override
    public List<DiseaseTreated> findAll() {
        return diseaseTreatedDAO.findAll();
    }

    @Override
    public DiseaseTreated findById(long id) {
        return diseaseTreatedDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("DiseaseTreated not found"));
    }

    @Override
    public DiseaseTreated save(DiseaseTreated diseaseTreated) {
        return diseaseTreatedDAO.save(diseaseTreated);
    }
    
}
