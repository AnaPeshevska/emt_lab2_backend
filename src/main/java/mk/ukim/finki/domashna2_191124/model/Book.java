package mk.ukim.finki.domashna2_191124.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue
    Long id;

    String name;

    @Enumerated(EnumType.STRING)
    Category category;

    @ManyToOne
    Author author;

    Integer availableCopies;

    public Book(String name, String category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = Category.valueOf(category);
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
