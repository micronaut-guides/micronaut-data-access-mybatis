package example.micronaut.genre;

import example.micronaut.PaginationArguments;
import example.micronaut.SortingArguments;
import example.micronaut.domain.Genre;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GenreRepository {

    @Select("select * from genre where id=#{id}")
    Genre findById(long id);

    @Insert("insert into genre(name) values(#{name})")
    @Options(useGeneratedKeys = true)
    void save(Genre genre);

    @Delete("delete from genre where id=#{id}")
    void deleteById(long id);

    @Update("update genre set name=#{name} where id=#{id}")
    void update(long id, String name);

    @Select("select * from genre order by ${sorting.sort} ${sorting.order} limit ${pagination.offset}, ${pagination.max}")
    List<Genre> findAll(@Param("pagination") PaginationArguments paginationArgs, @Param("sorting") SortingArguments sortingArgs);

}
