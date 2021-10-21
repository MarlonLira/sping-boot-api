package com.api.inventory.commons.mapper;

import com.api.inventory.dtos.UserDTO;
import com.api.inventory.models.UserModel;

import org.springframework.beans.BeanUtils;

public class GenericMapper {

  public static UserModel ToModel(UserDTO dto) {
    var _model = new UserModel();
    BeanUtils.copyProperties(dto, _model);
    return _model;
  }
}
