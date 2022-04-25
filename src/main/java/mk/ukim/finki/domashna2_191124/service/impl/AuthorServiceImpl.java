package mk.ukim.finki.domashna2_191124.service.impl;

import mk.ukim.finki.domashna2_191124.model.Author;
import mk.ukim.finki.domashna2_191124.model.Country;
import mk.ukim.finki.domashna2_191124.model.exeptions.InvalidAuthorIdException;
import mk.ukim.finki.domashna2_191124.repository.AuthorRepository;
import mk.ukim.finki.domashna2_191124.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository.findById(id).orElseThrow(InvalidAuthorIdException::new);
    }

    @Override
    public Author create(String name, String surname, Country country) {
        Author author = new Author(name, surname, country);
        return this.authorRepository.save(author);
    }

    @Override
    public Author update(Long id, String name, String surname, Country country) {
        Author author = this.findById(id);
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return this.authorRepository.save(author);
    }

    @Override
    public Author delete(Long id) {
        Author author = this.findById(id);
        this.authorRepository.delete(author);
        return author;
    }
}