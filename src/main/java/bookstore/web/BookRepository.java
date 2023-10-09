package bookstore.web;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

//@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

	List<Book> findByTitle(String title);
    
}