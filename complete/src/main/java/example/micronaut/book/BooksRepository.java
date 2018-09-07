package example.micronaut.book;

import example.micronaut.domain.Book;
import example.micronaut.domain.Genre;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BooksRepository {

    @Select("select * from book inner join genre on book.genre_id=genre.id where genre.id=#{id}")
    List<Book> findAllBooksByGenre(Long genreId);

    @Insert("insert into book(name, isbn, genre_id) values(#{name}, #{isbn}, #{genre.id})")
    @Options(useGeneratedKeys = true)
    void save(Book book);

    @Select("select * from book inner join genre on book.genre_id=genre.id where book.id=#{id}")
    Book findById(Long id);

    @Delete("delete from book where id=#{id}")
    void deleteById(Long id);

    @Select("select * from book")
    List<Book> findAll();

    @Update("update book set isbn=#{isbn}, name=#{name}, genre_id=#{genre.id} where id=#{id}")
    void update(@Param("id") long id, @Param("isbn") String isbn, @Param("name") String name, @Param("genre") Genre genre);
}
