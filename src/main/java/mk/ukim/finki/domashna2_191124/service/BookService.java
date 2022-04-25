package mk.ukim.finki.domashna2_191124.service;

import mk.ukim.finki.domashna2_191124.model.Book;
import mk.ukim.finki.domashna2_191124.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> listAll();

    Optional<Book> findById(Long id);

    Optional<Book> create(String name, String category, Long authorId, Integer availableCopies);

    Optional<Book> create(BookDto bookDto);

    Optional<Book> edit(Long id, String name, String category, Long authorId, Integer availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

    void markAsTaken(Long id);

}