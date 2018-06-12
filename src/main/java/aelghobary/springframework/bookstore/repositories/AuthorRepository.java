package aelghobary.springframework.bookstore.repositories;

import aelghobary.springframework.bookstore.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
