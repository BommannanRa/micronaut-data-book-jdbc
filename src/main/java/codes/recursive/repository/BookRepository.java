package codes.recursive.repository;

import codes.recursive.model.Book;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;


@JdbcRepository(dialect = Dialect.H2)
public interface BookRepository extends CrudRepository<Book, Long> {
    Book find(String title);
    List<Book> findByPagesGreaterThan(int pages);
    List<Book> findByPagesGreaterThanAndPagesLessThan(int start, int end);
    List<Book> findByTitleLike(String title);
    // The next line throws a compilation error:
    // List<Book> findByTitleILike(String title);
    // Unable to implement Repository method: BookRepository.findByTitleILike(String title). Cannot query entity [Book] on non-existent property: titleILike
    // List<Book> findByTitleILike(String title);
}