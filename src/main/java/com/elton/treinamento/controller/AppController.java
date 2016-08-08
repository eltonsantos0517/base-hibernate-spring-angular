package com.elton.treinamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elton.treinamento.model.entity.App;
import com.elton.treinamento.service.AppService;
import com.elton.treinamento.utils.AppUtil;

@RestController
public class AppController {

	@Autowired
	private AppService appService;

	@RequestMapping(value = "api/1/app", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String app() {
		try {
			final App app = new App();
			app.setDescrition("0123456789012345678901");
			appService.save(app);
			return app.getDescrition();
		} catch (TransactionSystemException e) {
			return AppUtil.extractMessage(e);
		}
	}
}
