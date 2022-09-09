package com.app.clinicon.country;

import java.util.List;


public interface CountryService {

    List<Country> findAll();
    Country findById(long id);
    Country save(Country country);

}
