package mk.ukim.finki.domashna2_191124.repository;

import mk.ukim.finki.domashna2_191124.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    void deleteByName(String name);
}
