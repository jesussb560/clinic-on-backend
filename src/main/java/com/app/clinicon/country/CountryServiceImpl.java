package com.app.clinicon.country;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryDAO countryDAO;
    
    @Override
    public List<Country> findAll() {
        return countryDAO.findAll();
    }

    @Override
    public Country findById(long id) {
        return countryDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("Country not found"));
    }

    @Override
    public Country save(Country country) {
        return countryDAO.save(country);
    }


    
}
