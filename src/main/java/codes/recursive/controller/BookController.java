package codes.recursive.controller;

import codes.recursive.model.Book;
import codes.recursive.repository.BookRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Post;

@Controller("/book")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Get("/")
    public HttpResponse index() {
        return HttpResponse.ok(
                bookRepository.findAll()
        );
    }

    @Post("/")
    public HttpResponse save(Book book) {
        return HttpResponse.created(
                bookRepository.save(book)
        );
    }

    @Get("/{id}")
    public HttpResponse getById(long id) {
        return HttpResponse.ok(
                bookRepository.findById(id)
        );
    }

    @Get("/title/{title}")
    public HttpResponse getByTitleLike(String title) {
        return HttpResponse.ok(
                bookRepository.findByTitleLike(title)
        );
    }
}