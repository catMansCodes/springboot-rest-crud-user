package org.catmanscodes.crud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@GetMapping("/welcome")
	public String welcomeUser() {
		return "Welcome to catMan's Codes !!!";
	}
}
