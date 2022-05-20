package com.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/mvc/author")
@Log4j2
public class AuthorMvcController {

	@GetMapping("/hello")
	public String hello() {
		log.info("hello world !!!!!!!!!!!!! ");
		return "hello.html";
	}
}
