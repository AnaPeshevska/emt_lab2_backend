package mk.ukim.finki.domashna2_191124.service.impl;

import mk.ukim.finki.domashna2_191124.model.Author;
import mk.ukim.finki.domashna2_191124.model.Book;
import mk.ukim.finki.domashna2_191124.model.Category;
import mk.ukim.finki.domashna2_191124.model.dto.BookDto;
import mk.ukim.finki.domashna2_191124.model.event.CreatedBookEvent;
import mk.ukim.finki.domashna2_191124.model.exeptions.InvalidAuthorIdException;
import mk.ukim.finki.domashna2_191124.model.exeptions.InvalidBookIdException;
import mk.ukim.finki.domashna2_191124.repository.AuthorRepository;
import mk.ukim.finki.domashna2_191124.repository.BookRepository;
import mk.ukim.finki.domashna2_191124.service.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Book> create(String name, String category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(InvalidAuthorIdException::new);
        this.bookRepository.deleteByName(name);
        Book book = new Book(name, category, author, availableCopies);
        this.applicationEventPublisher.publishEvent(new CreatedBookEvent(book));
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> create(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthorId()).orElseThrow(InvalidAuthorIdException::new);
        this.bookRepository.deleteByName(bookDto.getName());
        Book book = new Book(bookDto.getName(), String.valueOf(bookDto.getCategory()), author, bookDto.getAvailableCopies());
        this.applicationEventPublisher.publishEvent(new CreatedBookEvent(book));
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, String name, String category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(InvalidAuthorIdException::new);
        Book book = this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
        book.setName(name);
        book.setCategory(Category.valueOf(category));
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
        book.setName(book.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(book.getAuthor());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public void markAsTaken(Long id) {
        Book book = this.bookRepository.findById(id).orElseGet(null);
        if(book != null){
            book.setAvailableCopies(book.getAvailableCopies()-1);
            this.bookRepository.save(book);
        }
    }


}