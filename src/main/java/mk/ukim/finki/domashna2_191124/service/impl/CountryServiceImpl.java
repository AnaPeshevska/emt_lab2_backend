package mk.ukim.finki.domashna2_191124.service.impl;

import mk.ukim.finki.domashna2_191124.model.Country;
import mk.ukim.finki.domashna2_191124.model.exeptions.InvalidCountryIdException;
import mk.ukim.finki.domashna2_191124.repository.CountryRepository;
import mk.ukim.finki.domashna2_191124.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    public final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return this.countryRepository.findById(id).orElseThrow(InvalidCountryIdException::new);
    }

    @Override
    public Country create(String name, String continent) {
        Country country = new Country(name, continent);
        return this.countryRepository.save(country);
    }

    @Override
    public Country update(Long id, String name, String continent) {
        Country country = this.findById(id);
        country.setName(name);
        country.setContinent(continent);
        return this.countryRepository.save(country);
    }

    @Override
    public Country delete(Long id) {
        Country country = this.findById(id);
        this.countryRepository.delete(country);
        return country;
    }
}
