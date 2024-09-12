package techtask.techtaskacidsoft.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
public class Book {
    public enum Genre {
        FANTASY,
        MYSTERY,
        DETECTIVE,
        THRILLER,
        HORROR
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Max(value = 2024)
    private int publicationYear;
    private Genre genre;
    @Column(unique = true, length = 13)
    private String isbn;
}
