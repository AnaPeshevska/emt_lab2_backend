package mk.ukim.finki.domashna2_191124.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.domashna2_191124.model.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    String name;

    Category category;

    Long authorId;

    Integer availableCopies;

}
