package com.api.inventory.services;

import org.modelmapper.ModelMapper;

public class BaseService<TRepository> {
  protected final TRepository _repository;
  protected final ModelMapper _mapper;

  public BaseService(TRepository repository, ModelMapper mapper) {
    this._repository = repository;
    this._mapper = mapper;
  }
}
