package com.elton.treinamento.model.generic;

import java.io.Serializable;

public interface IEntity<K extends Serializable> extends Serializable {

	K getId();

	void setId(K value);
}
