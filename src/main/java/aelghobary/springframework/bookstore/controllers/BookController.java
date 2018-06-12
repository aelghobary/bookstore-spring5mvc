package aelghobary.springframework.bookstore.controllers;

import aelghobary.springframework.bookstore.model.Author;
import aelghobary.springframework.bookstore.model.Book;
import aelghobary.springframework.bookstore.repositories.AuthorRepository;
import aelghobary.springframework.bookstore.repositories.BookRepository;
import aelghobary.springframework.bookstore.repositories.PublisherRepository;
import aelghobary.springframework.bookstore.repositories.PublisherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class BookController {

    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;
    private AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/")
    public String getBooks(Model model){
        model.addAttribute("books", bookRepository.findAll());

        return "books";
    }

    @RequestMapping(path = "/books/add")
    public String createBook(Model model){

        model.addAttribute("book", new Book());
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("allAuthors", authorRepository.findAll());
        return "editBook";
    }

    @RequestMapping(path = "/books/edit/{id}")
    public String updateBook(Model model, @PathVariable(value = "id") Long id){

        Book book = bookRepository.findById(id).get();
        model.addAttribute("book", book);
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("allAuthors", authorRepository.findAll());

        return "editBook";
    }

    @PostMapping("/books")
    public String saveBook(Book book){
        bookRepository.save(book);
        return "redirect:/";
    }

    @RequestMapping("/books/delete/{id}")
    public String deleteBook(Model model, @PathVariable(value = "id") Long id){
        bookRepository.deleteById(id);
        return "redirect:/";
    }
}
