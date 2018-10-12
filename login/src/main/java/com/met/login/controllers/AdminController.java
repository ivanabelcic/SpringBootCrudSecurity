package com.met.login.controllers;


import java.math.BigInteger;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.met.login.entities.Book;
import com.met.login.entities.User;
import com.met.login.repository.BookRepository;
import com.met.login.services.UserService;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private BookRepository bookRepository;
	
	


	@GetMapping(value = "/admin/home")
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		model.addObject("user", user);
		model.addObject("book", new Book());

		List<Book> books = bookRepository.findAll();
		if (!books.isEmpty()) {
			model.addObject("books", books);
		}

		model.setViewName("admin/home");
		return model;
	}

	@PostMapping(value = "/admin/home")
	public ModelAndView addBook(@Valid Book book, BindingResult result) {
		ModelAndView model = new ModelAndView();
		bookRepository.save(book);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		model.addObject("user", user);
		model.addObject("book", new Book());

		List<Book> books = bookRepository.findAll();
		if (!books.isEmpty()) {
			model.addObject("books", books);
		}

		model.setViewName("admin/home");
		return model;
	}
	

	@RequestMapping(value="/updateBook/{id}", method=RequestMethod.GET)
	 public ModelAndView editBook(@PathVariable String id) {
	  ModelAndView model = new ModelAndView();	
	  Long idBook = Long.valueOf(id);
	  BigInteger idB = BigInteger.valueOf(idBook);
	  Book book = bookRepository.findById(idB).get();
	  model.addObject("book", book);
	  model.setViewName("admin/home_edit");	  
	  return model;
	 }
	 
	 @RequestMapping(value="/saveBook/{id}", method=RequestMethod.POST)
	 public ModelAndView save(@PathVariable Book book) {
	 ModelAndView model = new ModelAndView();
	 bookRepository.save(book);
	 model.setViewName("admin/home");
	 return model;
	 }
	 
		@RequestMapping(value = "/deleteBook/{id}", method = RequestMethod.GET)
		public ModelAndView deleteBook(@PathVariable String id) {	
			ModelAndView model = new ModelAndView("redirect:/admin/home");
			Long idBook = Long.valueOf(id);
			BigInteger idB = BigInteger.valueOf(idBook);
			bookRepository.deleteById(idB);
			return model;		
		}
}
