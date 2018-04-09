package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
// This populate the h2 database with data. This is done by listening for a spring lifecycle event.
// ContextRefreshedEvent - Event raised when an ApplicationContext gets initialized or refreshed.
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {

        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher testPub = new Publisher("Test Publisher", "1234 Google street");

        Book ddd = new Book("Domain Driven Design", "1234", testPub);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(testPub);
        bookRepository.save(ddd);


        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher testPub2 = new Publisher("Test Publisher", "1234 Google street");
        Book noEJB = new Book("J2EE Development without EJB", "23444", testPub2);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        publisherRepository.save(testPub2);
        bookRepository.save(noEJB);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }
}
