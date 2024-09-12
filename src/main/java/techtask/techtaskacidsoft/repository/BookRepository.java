package techtask.techtaskacidsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techtask.techtaskacidsoft.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
