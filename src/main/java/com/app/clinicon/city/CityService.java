package com.app.clinicon.city;

import java.util.List;

public interface CityService {

    List<City> findAll();
    City findById(long id);
    City save(City city);

}
