package techtask.techtaskacidsoft.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import techtask.techtaskacidsoft.dto.BookDto;
import techtask.techtaskacidsoft.dto.CreateBookRequestDto;
import techtask.techtaskacidsoft.dto.UpdateBookRequestDto;
import techtask.techtaskacidsoft.exception.EntityNotFoundException;
import techtask.techtaskacidsoft.mapper.BookMapper;
import techtask.techtaskacidsoft.model.Book;
import techtask.techtaskacidsoft.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto createBook(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Can't find book by id " + id));
        return bookMapper.toDto(book);
    }

    @Override
    public BookDto updateBookById(Long id, UpdateBookRequestDto requestDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Can't find book by id " + id));
        book.setTitle(requestDto.getTitle());
        book.setAuthor(requestDto.getAuthor());
        book.setPublicationYear(requestDto.getPublicationYear());
        book.setGenre(requestDto.getGenre());
        book.setIsbn(requestDto.getIsbn());
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
