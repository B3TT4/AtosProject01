package com.robertapretto.AtosProjectAPI.GeneralRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robertapretto.AtosProjectAPI.GeneralModels.ElectionModel;

@Repository
public interface ElectionRepository extends JpaRepository<ElectionModel, Long>{
	
}


