package com.jester.booklist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String MainGet(Model model) {
		model.addAttribute("title", "Главная страница");
		return "index";
	}
	
	@GetMapping("/contacts")
	public String ContactsGet(Model model) {
		model.addAttribute("title", "Контакты");
		return "contacts";
	}
}
