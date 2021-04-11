package com.robertapretto.AtosProjectAPI.GeneralRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robertapretto.AtosProjectAPI.GeneralModels.ProfessorModel;


@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorModel, Long>{

}
