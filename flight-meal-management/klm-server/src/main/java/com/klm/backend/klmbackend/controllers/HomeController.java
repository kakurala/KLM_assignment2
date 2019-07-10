package com.klm.backend.klmbackend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	/**
	 * Angular app entry point
	 * @return
	 */
	@RequestMapping("/home")
	public String home() {
		return "forward:/index.html";
	}

}
