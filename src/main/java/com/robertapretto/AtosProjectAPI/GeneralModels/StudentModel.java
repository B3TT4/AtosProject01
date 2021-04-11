package com.robertapretto.AtosProjectAPI.GeneralModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.annotations.ApiModelProperty;

@EnableJpaRepositories
@Entity
public class StudentModel{
	
	@ApiModelProperty(notes = "ID do estudante Registrado", name="id", required=true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@ApiModelProperty(notes = "Nome do estudante resgistrado", name="name")
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
