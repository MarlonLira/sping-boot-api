package com.api.inventory.services.interfaces;

import java.util.List;

import com.api.inventory.models.UserModel;

public interface IUserService {

  public Integer save(UserModel model);

  public void update(UserModel model) throws Exception;

  public void delete(Integer id);

  public String login(UserModel model);

  public List<UserModel> getAll();

}
