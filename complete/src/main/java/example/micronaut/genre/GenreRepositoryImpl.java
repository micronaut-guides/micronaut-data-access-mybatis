package example.micronaut.genre;

import example.micronaut.PaginationArguments;
import example.micronaut.SortingArguments;
import example.micronaut.domain.Genre;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.inject.Singleton;
import java.util.List;

@Singleton // <1>
public class GenreRepositoryImpl implements GenreRepository {

    private final SqlSessionFactory sqlSessionFactory; // <2>

    public GenreRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory; // <2>
    }

    @Override
    public Genre findById(long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) { // <3>
            GenreRepository genreMapper = sqlSession.getMapper(GenreRepository.class); // <4>
            return genreMapper.findById(id); // <5>
        }
    }

    @Override
    public void save(Genre genre) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GenreRepository genreMapper = sqlSession.getMapper(GenreRepository.class);
            genreMapper.save(genre);
            sqlSession.commit(); // <6>
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GenreRepository genreMapper = sqlSession.getMapper(GenreRepository.class);
            genreMapper.deleteById(id);
            sqlSession.commit();
        }
    }

    @Override
    public void update(long id, String name) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GenreRepository genreMapper = sqlSession.getMapper(GenreRepository.class);
            genreMapper.update(id, name);
            sqlSession.commit();
        }
    }

    @Override
    public List<Genre> findAll(PaginationArguments paginationArgs, SortingArguments sortingArgs) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GenreRepository genreMapper = sqlSession.getMapper(GenreRepository.class);
            return genreMapper.findAll(paginationArgs, sortingArgs);
        }
    }
}
