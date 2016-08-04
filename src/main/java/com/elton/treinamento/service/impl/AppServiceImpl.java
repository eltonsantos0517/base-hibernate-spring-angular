package com.elton.treinamento.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elton.treinamento.model.dao.AppDAO;
import com.elton.treinamento.model.entity.App;
import com.elton.treinamento.service.AppService;


@Service
public class AppServiceImpl implements AppService{
	
	@Autowired
	private AppDAO appDao;

	@Override
	@Transactional
	public App save(App app) {
		return appDao.save(app);
	}
	
	

}
