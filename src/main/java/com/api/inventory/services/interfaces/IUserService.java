package com.api.inventory.services.interfaces;

import java.util.List;

import com.api.inventory.models.UserModel;

public interface IUserService {

  public Integer save(UserModel model);

  public String Login(UserModel model);

  public List<UserModel> getAll();

}
