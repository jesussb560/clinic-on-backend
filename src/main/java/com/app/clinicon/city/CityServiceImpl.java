package com.app.clinicon.city;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityDAO cityDAO;

    @Override
    public List<City> findAll() {
        return findAll();
    }

    @Override
    public City findById(long id) {
        return cityDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("City not found"));
    }

    @Override
    public City save(City city) {
        return cityDAO.save(city);
    }
    
}
