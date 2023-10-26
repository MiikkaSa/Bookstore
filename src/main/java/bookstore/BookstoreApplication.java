package bookstore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import bookstore.com.AppUser;
import bookstore.com.AppUserRepository;
import bookstore.com.Book;
import bookstore.com.BookRepository;
import bookstore.com.Category;
import bookstore.com.CategoryRepository;


@SpringBootApplication
@ComponentScan("bookstore.web.BookRepository")
public class BookstoreApplication {

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository crepository, AppUserRepository urepository) {
        return (args) -> {
            log.info("categories");
            crepository.save(new Category("Fantasy"));
            crepository.save(new Category("Horror"));
            crepository.save(new Category("Fiction"));
	
            bookRepository.save(new Book("Seikkailukirja", "Miikka Savolainen", 2023, "123-123", 50.00, 
            		crepository.findByName("Horror").get(0)));
          
	AppUser user1 = new AppUser("user", "user@gmail.com","$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6" ,"USER");
	AppUser user2 = new AppUser("admin", "admin@gmail.com","$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C" ,"ADMIN");
	urepository.save(user1);
	urepository.save(user2);
	
	log.info("fetch all books");
	for (Book book : bookRepository.findAll()) {
		log.info(book.toString());
}

	};
	}
}


