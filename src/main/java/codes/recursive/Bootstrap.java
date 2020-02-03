package codes.recursive;

import codes.recursive.model.Book;
import codes.recursive.repository.BookRepository;
import io.micronaut.discovery.event.ServiceStartedEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Async;

import javax.inject.Singleton;
import java.sql.Date;
import java.time.LocalDate;

@Singleton
public class Bootstrap {
    private final BookRepository bookRepository;

    public Bootstrap(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener
    @Async
    public void loadData(final ServiceStartedEvent event) {
        System.out.println("service started!");

        Book book1 = new Book("Semicolons; Are They Useless?", 1000, Date.valueOf(LocalDate.of(2020, 01, 01)));
        bookRepository.save(book1);

        Book book2 = new Book("Polymorphism: That's Not What I Thought It Was...", 250, Date.valueOf(LocalDate.of(2000, 10, 01)));
        bookRepository.save(book2);

        Book book3 = new Book("From Maker To Baker: How To Make The Perfect Raspberry Pie", 25, Date.valueOf(LocalDate.of(2020, 01, 01)));
        bookRepository.save(book3);

        Book book4 = new Book("How Microservices Ruined My Marriage", 375, Date.valueOf(LocalDate.of(2017, 07, 01)));
        bookRepository.save(book4);

        System.out.println("books created!");
    }
}