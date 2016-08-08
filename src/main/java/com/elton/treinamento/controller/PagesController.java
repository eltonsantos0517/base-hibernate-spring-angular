package com.elton.treinamento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class PagesController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAppPage() {
		return "index";
	}
}
