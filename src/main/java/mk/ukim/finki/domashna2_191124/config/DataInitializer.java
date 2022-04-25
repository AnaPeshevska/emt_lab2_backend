package mk.ukim.finki.domashna2_191124.config;

import mk.ukim.finki.domashna2_191124.model.Author;
import mk.ukim.finki.domashna2_191124.model.Country;
import mk.ukim.finki.domashna2_191124.service.AuthorService;
import mk.ukim.finki.domashna2_191124.service.BookService;
import mk.ukim.finki.domashna2_191124.service.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataInitializer {

    private final AuthorService authorService;
    private final BookService bookService;
    private final CountryService countryService;


    public DataInitializer(AuthorService authorService, BookService bookService, CountryService countryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.countryService = countryService;
    }

    @PostConstruct
    public void initData(){
        this.countryService.create("England", "Europe");
        this.countryService.create("USA", "North America");
        List<Country> countries = this.countryService.listAll();

        this.authorService.create("J.K.", "Rowling", countries.get(0));
        this.authorService.create("Agata", "Christie", countries.get(0));
        this.authorService.create("John", "Green", countries.get(1));
        List<Author> authors = authorService.listAll();

        this.bookService.create("Harry Potter", "FANTASY", authors.get(0).getId(), 1000);
        this.bookService.create("Murder on the Orient Express", "THRILER", authors.get(1).getId(), 1500);
        this.bookService.create("Looking for Alaska", "NOVEL", authors.get(2).getId(), 2000);

    }

}