package com.robertapretto.AtosProjectAPI.GeneralRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robertapretto.AtosProjectAPI.GeneralModels.CensusModel;

@Repository
public interface CensusRepository extends JpaRepository<CensusModel, Long>{
	
}
