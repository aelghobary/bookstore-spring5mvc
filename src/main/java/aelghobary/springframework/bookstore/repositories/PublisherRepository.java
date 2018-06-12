package aelghobary.springframework.bookstore.repositories;

import aelghobary.springframework.bookstore.model.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
