package bookstore.com;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import bookstore.web.Book;
import bookstore.web.BookRepository;
import bookstore.web.CategoryRepository;




@Controller
public class BookController {

	@Autowired
	private BookRepository repository;

	@Autowired
	private CategoryRepository crepository;

	// BOOK CONTROLLER
	

	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());

		return "booklist";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	
	
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }    

	
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    }  

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long Id, Model model) {
		repository.deleteById(Id);
		return "redirect:../booklist";
	}


	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateBook(@ModelAttribute Book updatedBook) {

		Book existingBook = repository.findById(updatedBook.getId()).orElse(null);
		if (existingBook != null) {

			existingBook.setAuthor(updatedBook.getAuthor());
			existingBook.setTitle(updatedBook.getTitle());
			existingBook.setIsbn(updatedBook.getIsbn());
			existingBook.setPublicationYear(updatedBook.getPublicationYear());
			existingBook.setCategory(updatedBook.getCategory());

			repository.save(existingBook);
			return "redirect:/booklist";

		} else {
			return "redirect:/booklist";
		}

	}
}
