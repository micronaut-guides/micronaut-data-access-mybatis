package example.micronaut.genre;

import example.micronaut.PaginationArguments;
import example.micronaut.SortingArguments;
import example.micronaut.domain.Genre;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class GenreRepositoryImpl implements GenreRepository {

    private final SqlSessionFactory sqlSessionFactory;

    public GenreRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Genre findById(long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GenreRepository genreMapper = sqlSession.getMapper(GenreRepository.class);
            return genreMapper.findById(id);
        }
    }

    @Override
    public void save(Genre genre) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GenreRepository genreMapper = sqlSession.getMapper(GenreRepository.class);
            genreMapper.save(genre);
            sqlSession.commit();
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
