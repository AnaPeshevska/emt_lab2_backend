package mk.ukim.finki.domashna2_191124.service;

import mk.ukim.finki.domashna2_191124.model.Author;
import mk.ukim.finki.domashna2_191124.model.Country;

import java.util.List;

public interface AuthorService {

    List<Author> listAll();

    Author findById(Long id);

    Author create(String name, String surname, Country country);

    Author update(Long id, String name, String surname, Country country);

    Author delete(Long id);

}
