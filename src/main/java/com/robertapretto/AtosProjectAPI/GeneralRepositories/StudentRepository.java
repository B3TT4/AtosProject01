package com.robertapretto.AtosProjectAPI.GeneralRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robertapretto.AtosProjectAPI.GeneralModels.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long>{


}
