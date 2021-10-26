package com.api.inventory.services.interfaces;

import java.util.List;

import com.api.inventory.dtos.User.UserCreateDTO;
import com.api.inventory.dtos.User.UserDTO;
import com.api.inventory.dtos.User.UserUpdateDTO;

public interface IUserService {

  public Integer save(UserCreateDTO dto);

  public void update(UserUpdateDTO dto);

  public void delete(Integer id);

  public String login(UserCreateDTO dto);

  public List<UserDTO> getAll();

}
