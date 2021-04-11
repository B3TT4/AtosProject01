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
public class CensusModel {
	
	@ApiModelProperty(notes = "ID da coleta", name="id", required=true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@ApiModelProperty(notes = "Ano da coleta", name="year")
	@Column(nullable = false)
	public Integer year;
	
	@ApiModelProperty(notes = "Número de pessoas", name="peoples")
	@Column(nullable = false)
	public Float peoples;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Float getPeoples() {
		return peoples;
	}

	public void setPeoples(Float peoples) {
		this.peoples = peoples;
	}
	
	

}
