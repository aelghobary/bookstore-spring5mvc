package aelghobary.springframework.bookstore.bootstrap;

import aelghobary.springframework.bookstore.model.Author;
import aelghobary.springframework.bookstore.model.Book;
import aelghobary.springframework.bookstore.model.Publisher;
import aelghobary.springframework.bookstore.repositories.AuthorRepository;
import aelghobary.springframework.bookstore.repositories.BookRepository;
import aelghobary.springframework.bookstore.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public Bootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        initData();
    }

    private void initData(){

        Author eric = new Author("Eric" , "Evans");
        Publisher ahmedPress = new Publisher("Evans Press", "8350 Greensboro Dr.");
        Book ddd = new Book("Domain Driven Design", "123456", ahmedPress);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        publisherRepository.save(ahmedPress);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author cal = new Author("Cal" , "Newport");
        Publisher newportPress = new Publisher("Newport Press", "Springhill road");
        Book deepWork = new Book("Deep Work", "987654", newportPress);
        cal.getBooks().add(deepWork);
        deepWork.getAuthors().add(cal);

        publisherRepository.save(newportPress);
        authorRepository.save(cal);
        bookRepository.save(deepWork);
    }
}
