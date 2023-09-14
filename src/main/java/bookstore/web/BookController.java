package bookstore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bookstore.domain.*;
import bookstore.domain.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;

	//BOOK CONTROLLER
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());

		return "booklist";
	}
	
	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}

	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	

	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long Id, Model model) {
	repository.deleteById(Id);
	return "redirect:../booklist";
	}
	
	
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long Id, Model model) {

		Optional<Book> optionalBook = repository.findById(Id);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			model.addAttribute("book", book);
			return "editbook";
		} else {

			return "redirect:/booklist";
		}
	}

	

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateBook(@ModelAttribute Book updatedBook) {
		
		Book existingBook = repository.findById(updatedBook.getId()).orElse(null);
	    if (existingBook != null) {
	        
	        existingBook.setAuthor(updatedBook.getAuthor());
	        existingBook.setTitle(updatedBook.getTitle());
	        existingBook.setIsbn(updatedBook.getIsbn());
	        existingBook.setPublicationYear(updatedBook.getPublicationYear());
	        
	        repository.save(existingBook);
	        return "redirect:/booklist";
	        
	    } else {
	        return "redirect:/booklist";
	    }
}
}
