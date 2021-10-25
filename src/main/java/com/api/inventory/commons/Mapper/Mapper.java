package com.api.inventory.commons.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class Mapper {

  public static <S, T> List<T> mapList(ModelMapper mapper, List<S> source, Class<T> targetClass) {
    return source.stream().map(element -> mapper.map(element, targetClass)).collect(Collectors.toList());
  }

}
