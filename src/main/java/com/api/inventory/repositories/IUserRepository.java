package com.api.inventory.repositories;

import java.util.Optional;

import com.api.inventory.models.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Integer> {

  public Optional<UserModel> findByLogin(String login);

}
