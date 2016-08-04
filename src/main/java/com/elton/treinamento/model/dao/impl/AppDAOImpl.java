package com.elton.treinamento.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.elton.treinamento.model.dao.AppDAO;
import com.elton.treinamento.model.entity.App;
import com.elton.treinamento.model.generic.AbstractDao;

@Repository
public class AppDAOImpl  extends AbstractDao<App, Long> implements AppDAO {

	public AppDAOImpl() {
		super(App.class);
	}

}
