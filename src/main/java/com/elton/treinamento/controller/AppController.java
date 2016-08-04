package com.elton.treinamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elton.treinamento.model.entity.App;
import com.elton.treinamento.service.AppService;


@RestController
public class AppController {

	@Autowired
	private AppService appService;

	@RequestMapping("/app")
	public String app() throws Exception {
		final App app = new App();
		app.setDescrition("A really cool description :)");
		appService.save(app);
		return app.getDescrition();
	}
}
