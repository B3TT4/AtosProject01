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
public class ElectionModel {

	@ApiModelProperty(notes = "ID da votação", name="id", required=true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@ApiModelProperty(notes = "Candidato", name="code")
	@Column(nullable = false)
	public Integer code;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	
}
