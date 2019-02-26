package example.micronaut.genre;

import example.micronaut.domain.Genre;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Singleton // <1>
public class GenreMapperImpl implements GenreMapper {

    private final SqlSessionFactory sqlSessionFactory; // <2>

    public GenreMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory; // <2>
    }

    @Override
    public Genre findById(long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) { // <3>
            GenreMapper genreMapper = sqlSession.getMapper(GenreMapper.class); // <4>
            return genreMapper.findById(id); // <5>
        }
    }

    @Override
    public void save(Genre genre) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GenreMapper genreMapper = sqlSession.getMapper(GenreMapper.class);
            genreMapper.save(genre);
            sqlSession.commit(); // <6>
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GenreMapper genreMapper = sqlSession.getMapper(GenreMapper.class);
            genreMapper.deleteById(id);
            sqlSession.commit();
        }
    }

    @Override
    public void update(long id, String name) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GenreMapper genreMapper = sqlSession.getMapper(GenreMapper.class);
            genreMapper.update(id, name);
            sqlSession.commit();
        }
    }

    @Override
    public List<Genre> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GenreMapper genreMapper = sqlSession.getMapper(GenreMapper.class);
            return genreMapper.findAll();
        }
    }

    @Override
    public List<Genre> findAllBySortAndOrder(@NotNull @Pattern(regexp = "id|name") String sort,
                                             @NotNull @Pattern(regexp = "asc|ASC|desc|DESC") String order) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GenreMapper genreMapper = sqlSession.getMapper(GenreMapper.class);
            return genreMapper.findAllBySortAndOrder(sort, order);
        }
    }

    @Override
    public List<Genre> findAllByOffsetAndMaxAndSortAndOrder(@NotNull @PositiveOrZero Integer offset,
                                                            @Positive @NotNull Integer max,
                                                            @NotNull @Pattern(regexp = "id|name") String sort,
                                                            @NotNull @Pattern(regexp = "asc|ASC|desc|DESC") String order) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GenreMapper genreMapper = sqlSession.getMapper(GenreMapper.class);
            return genreMapper.findAllByOffsetAndMaxAndSortAndOrder(offset, max, sort, order);
        }
    }

    @Override
    public List<Genre> findAllByOffsetAndMax(@NotNull @PositiveOrZero Integer offset,
                                             @Positive @NotNull Integer max) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GenreMapper genreMapper = sqlSession.getMapper(GenreMapper.class);
            return genreMapper.findAllByOffsetAndMax(offset, max);
        }
    }
}
