package com.app.clinicon.activationtoken;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationTokenDAO extends JpaRepository<ActivationToken, Long> {

    Optional<ActivationToken> findByToken(String token);
    
}
