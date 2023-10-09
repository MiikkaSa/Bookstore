package bookstore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import bookstore.web.AppUser;
import bookstore.web.AppUserRepository;
import bookstore.web.Book;
import bookstore.web.BookRepository;
import bookstore.web.Category;
import bookstore.web.CategoryRepository;


@SpringBootApplication
@ComponentScan("bookstore.web.BookRepository")
public class BookstoreApplication {

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    private final BookRepository bookRepository; // Constructor injection

    public BookstoreApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner bookDemo(CategoryRepository crepository, AppUserRepository urepository) {
        return (args) -> {
            log.info("categories");
            crepository.save(new Category("Fantasy"));
            crepository.save(new Category("Horror"));
            crepository.save(new Category("Fiction"));
	
	AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@gmail.com", "USER");
	AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@gmail.com", "ADMIN");
	urepository.save(user1);
	urepository.save(user2);
	
	log.info("fetch all books");
	for (Book book : bookRepository.findAll()) {
		log.info(book.toString());
}

	};
	}
}


