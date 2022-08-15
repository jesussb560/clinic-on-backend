package com.app.clinicon.activationtoken;

import java.util.List;

public interface ActivationTokenService {

    List<ActivationToken> findAll();
    ActivationToken findById(long id);
    ActivationToken save(ActivationToken activationToken);

    ActivationToken findByToken(String token);
    
}
