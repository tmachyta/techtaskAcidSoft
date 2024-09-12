package techtask.techtaskacidsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import techtask.techtaskacidsoft.config.MapperConfig;
import techtask.techtaskacidsoft.dto.BookDto;
import techtask.techtaskacidsoft.dto.CreateBookRequestDto;
import techtask.techtaskacidsoft.model.Book;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    Book toModel(CreateBookRequestDto requestDto);
}
