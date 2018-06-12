package aelghobary.springframework.bookstore.controllers;

import aelghobary.springframework.bookstore.model.Author;
import aelghobary.springframework.bookstore.repositories.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorController {
    private AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/authors")
    public String getAuthors(Model model){
        model.addAttribute("authors", authorRepository.findAll());
        return "authors";
    }

    @RequestMapping(path = "/authors/add")
    public String createAuthor(Model model){
        model.addAttribute("author", new Author());
        return "editAuthor";
    }

    @RequestMapping(path = "/authors/edit/{id}")
    public String updateAuthor(Model model, @PathVariable(value = "id") Long id){

        Author author = authorRepository.findById(id).get();
        model.addAttribute("author", author);
        return "editAuthor";
    }

    @PostMapping("/authors")
    public String saveAuthor(Author author){
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @RequestMapping("/authors/delete/{id}")
    public String deleteAuthor(Model model, @PathVariable(value = "id") Long id){
        authorRepository.deleteById(id);
        return "redirect:/authors";
    }
}
