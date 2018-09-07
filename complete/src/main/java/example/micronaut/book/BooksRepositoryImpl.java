package example.micronaut.book;

import example.micronaut.domain.Book;
import example.micronaut.domain.Genre;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class BooksRepositoryImpl implements BooksRepository {

    private final SqlSessionFactory sqlSessionFactory;

    public BooksRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<Book> findAllBooksByGenre(Long genreId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BooksRepository bookMapper = sqlSession.getMapper(BooksRepository.class);
            return bookMapper.findAllBooksByGenre(genreId);
        }
    }

    @Override
    public void save(Book book) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BooksRepository bookMapper = sqlSession.getMapper(BooksRepository.class);
            bookMapper.save(book);
            sqlSession.commit();
        }
    }

    @Override
    public Book findById(Long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BooksRepository bookMapper = sqlSession.getMapper(BooksRepository.class);
            return bookMapper.findById(id);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BooksRepository bookMapper = sqlSession.getMapper(BooksRepository.class);
            bookMapper.deleteById(id);
            sqlSession.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Book> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BooksRepository bookMapper = sqlSession.getMapper(BooksRepository.class);
            return bookMapper.findAll();
        }
    }

    @Override
    public void update(long id, String isbn, String name, Genre genre) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BooksRepository bookMapper = sqlSession.getMapper(BooksRepository.class);
            bookMapper.update(id, isbn, name, genre);
            sqlSession.commit();
        }
    }
}
