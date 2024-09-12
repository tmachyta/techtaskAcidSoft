package techtask.techtaskacidsoft.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import techtask.techtaskacidsoft.model.Book;

@Data
@Accessors(chain = true)
public class CreateBookRequestDto {
    private String title;
    private String author;
    private int publicationYear;
    private Book.Genre genre;
    private String isbn;
}
