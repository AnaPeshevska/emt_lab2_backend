package mk.ukim.finki.domashna2_191124.web;

import mk.ukim.finki.domashna2_191124.model.Book;
import mk.ukim.finki.domashna2_191124.model.dto.BookDto;
import mk.ukim.finki.domashna2_191124.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/listAll")
    public List<Book> listAll() {
        return this.bookService.listAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return this.bookService.findById(id).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/books/addBook")
    public ResponseEntity<Book> create(@RequestBody BookDto bookDto){
        return this.bookService.create(bookDto).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/books/editBook/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto){
        return this.bookService.edit(id,bookDto).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/books/deleteBook/{id}")
    public ResponseEntity<Book> deleteById(@PathVariable Long id){
        this.bookService.deleteById(id);
        if(this.bookService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/books/markBookAsTaken/{id}")
    public Book markAsTaken(@PathVariable Long id){
        this.bookService.markAsTaken(id);
        return this.bookService.findById(id).orElseGet(null);
    }

}