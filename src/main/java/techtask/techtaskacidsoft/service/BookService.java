package techtask.techtaskacidsoft.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import techtask.techtaskacidsoft.dto.BookDto;
import techtask.techtaskacidsoft.dto.CreateBookRequestDto;
import techtask.techtaskacidsoft.dto.UpdateBookRequestDto;

public interface BookService {
    List<BookDto> getAll(Pageable pageable);

    BookDto createBook(CreateBookRequestDto requestDto);

    BookDto getBookById(Long id);

    BookDto updateBookById(Long id, UpdateBookRequestDto requestDto);

    void deleteById(Long id);
}
