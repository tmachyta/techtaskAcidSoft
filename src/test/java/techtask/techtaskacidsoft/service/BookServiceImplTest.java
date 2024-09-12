package techtask.techtaskacidsoft.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import techtask.techtaskacidsoft.dto.BookDto;
import techtask.techtaskacidsoft.dto.CreateBookRequestDto;
import techtask.techtaskacidsoft.dto.UpdateBookRequestDto;
import techtask.techtaskacidsoft.exception.EntityNotFoundException;
import techtask.techtaskacidsoft.mapper.BookMapper;
import techtask.techtaskacidsoft.model.Book;
import techtask.techtaskacidsoft.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    private static final Long BOOK_ID = 1L;
    private static final Long NON_EXISTED_ID = 50L;
    private static final String OLD_TITLE = "OLD_T";
    private static final String OLD_AUTHOR = "OLD_A";
    private static final String NEW_TITLE = "NEW_T";
    private static final String NEW_AUTHOR = "NEW_A";

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void testSuccessfullySaveTrainingProgram() {
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        BookDto bookDto = new BookDto();
        Book bookToSave = new Book();

        when(bookMapper.toModel(requestDto)).thenReturn(bookToSave);

        when(bookRepository.save(bookToSave)).thenReturn(bookToSave);

        when(bookMapper.toDto(bookToSave)).thenReturn(bookDto);

        BookDto result = bookService.createBook(requestDto);

        assertNotNull(result);
        assertEquals(bookDto, result);
    }

    @Test
    public void testGetAllBooks() {
        Book book = new Book();
        Pageable pageable = PageRequest.of(0, 10);
        List<Book> books = List.of(new Book());
        List<BookDto> expectedBooks = List.of(new BookDto());
        Page<Book> page = new PageImpl<>(books, pageable, books.size());

        when(bookRepository.findAll(pageable)).thenReturn(page);

        when(bookMapper.toDto(book)).thenReturn(new BookDto());

        List<BookDto> result = bookService.getAll(pageable);

        Assertions.assertEquals(expectedBooks.size(), result.size());
    }

    @Test
    public void testFindBookById() {
        Book book = new Book()
                .setId(BOOK_ID);

        BookDto bookDto = new BookDto()
                .setId(BOOK_ID);

        when(bookRepository.findById(book.getId()))
                .thenReturn(Optional.of(book));

        when(bookMapper.toDto(book)).thenReturn(bookDto);

        BookDto result = bookService.getBookById(BOOK_ID);

        Assertions.assertEquals(book.getId(), result.getId());
    }

    @Test
    public void testFindBookNonExistedId() {
        when(bookRepository.findById(NON_EXISTED_ID))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> bookService.getBookById(NON_EXISTED_ID));
    }

    @Test
    public void testDeleteBookById() {
        bookService.deleteById(BOOK_ID);

        when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> bookService.getBookById(BOOK_ID));
    }

    @Test
    public void testUpdateBookSuccessfully() {
        UpdateBookRequestDto request = new UpdateBookRequestDto()
                .setTitle(OLD_TITLE)
                .setAuthor(OLD_AUTHOR);

        BookDto expectedResult = new BookDto()
                .setTitle(NEW_TITLE)
                .setAuthor(NEW_AUTHOR);

        Book book = new Book()
                .setTitle(OLD_TITLE)
                .setAuthor(OLD_TITLE);

        when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.of(book));

        when(bookRepository.save(book)).thenReturn(book);

        when(bookMapper.toDto(book)).thenReturn(expectedResult);

        BookDto updatedBook = bookService.updateBookById(BOOK_ID, request);

        Assertions.assertEquals(updatedBook.getTitle(), expectedResult.getTitle());

        Assertions.assertEquals(updatedBook.getAuthor(), expectedResult.getAuthor());
    }
}
