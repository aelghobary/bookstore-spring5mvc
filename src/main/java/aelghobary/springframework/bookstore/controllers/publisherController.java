package aelghobary.springframework.bookstore.controllers;

import aelghobary.springframework.bookstore.model.Publisher;
import aelghobary.springframework.bookstore.repositories.PublisherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class publisherController {
    
    private PublisherRepository publisherRepository;

    public publisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @RequestMapping("/publishers")
    public String getPublishers(Model model){
        model.addAttribute("publishers", publisherRepository.findAll());
        return "publishers";
    }

    @RequestMapping(path = "/publishers/add")
    public String createPublisher(Model model){
        model.addAttribute("publisher", new Publisher());
        return "editpublisher";
    }

    @RequestMapping(path = "/publishers/edit/{id}")
    public String updatePublisher(Model model, @PathVariable(value = "id") Long id){

        Publisher publisher = publisherRepository.findById(id).get();
        model.addAttribute("publisher", publisher);
        return "editpublisher";
    }

    @PostMapping("/publishers")
    public String savePublisher(Publisher publisher){
        publisherRepository.save(publisher);
        return "redirect:/publishers";
    }

    @RequestMapping("/publishers/delete/{id}")
    public String deletePublisher(Model model, @PathVariable(value = "id") Long id){
        publisherRepository.deleteById(id);
        return "redirect:/publishers";
    }
}
