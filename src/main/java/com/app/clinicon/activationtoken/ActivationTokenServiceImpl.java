package com.app.clinicon.activationtoken;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ActivationTokenServiceImpl implements ActivationTokenService {

    private final ActivationTokenDAO activationTokenDAO;

    @Override
    public List<ActivationToken> findAll() {
        return activationTokenDAO.findAll();
    }

    @Override
    public ActivationToken findById(long id) {
        return activationTokenDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("ActivateToken not found"));
    }

    @Override
    public ActivationToken save(ActivationToken activationToken) {
        return activationTokenDAO.save(activationToken);
    }

    @Override
    public ActivationToken findByToken(String token) {
        return activationTokenDAO.findByToken(token).orElseThrow(() -> new EntityNotFoundException("ActivateToken not found"));
    }
    
}
