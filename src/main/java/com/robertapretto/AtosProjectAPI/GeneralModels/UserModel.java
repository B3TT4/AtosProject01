package com.robertapretto.AtosProjectAPI.GeneralModels;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

public class UserModel {
	
	@ApiModelProperty(notes = "ID do Registrado", name="id", required=true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@ApiModelProperty(notes = "Nome do resgistrado", name="name")
	@Column(nullable = false)
	public String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
