package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import bookstore.BookstoreApplication;
import bookstore.com.Book;
import bookstore.com.BookRepository;

//@ExtendWith(SpringExtension.class)
//@DataJpaTest

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    public void findByTitle() {
        List<Book> books = repository.findByTitle("Johnson");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("John");
    }
    
    @Test
    public void createNewBook() {
    	Book book = new Book();
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    
    @Test
    public void deleteBook() {
		List<Book> books = repository.findByTitle("Johnson");
		Book book = books.get(0);
		repository.delete(book);
		List<Book> newBooks = repository.findByTitle("Johnson");
		assertThat(newBooks).hasSize(0);
     }

}

