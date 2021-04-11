package com.robertapretto.AtosProjectAPI.GeneralRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robertapretto.AtosProjectAPI.GeneralModels.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

}
