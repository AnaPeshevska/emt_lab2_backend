package mk.ukim.finki.domashna2_191124.service;

import mk.ukim.finki.domashna2_191124.model.Country;

import java.util.List;

public interface CountryService {

    List<Country> listAll();

    Country findById(Long id);

    Country create(String name, String continent);

    Country update(Long id, String name, String continent);

    Country delete(Long id);

}
