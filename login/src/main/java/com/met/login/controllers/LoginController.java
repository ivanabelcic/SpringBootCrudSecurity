package com.met.login.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.met.login.entities.User;
import com.met.login.services.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = { "/", "/login" })
	public String login() {
		return "login";
	}

	@GetMapping(value = "/registration")
	public ModelAndView registration() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("user", user);
		model.setViewName("registration");
		return model;
	}

	@PostMapping(value = "/registration")
	public ModelAndView createUser(@Valid User user, BindingResult result) {
		ModelAndView model = new ModelAndView();

		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null)
			result.rejectValue("email", "Korisnik je vec registrovan");
		if (result.hasErrors())
			model.setViewName("registration");
		else {
			userService.saveUser(user);
			model.addObject("successMessage", "Uspesno ste kreirari nalog");
			model.addObject("user", new User());
			model.setViewName("registration");
		}

		return model;
	}
	
}
