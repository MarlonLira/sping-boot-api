package com.api.inventory.commons;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class Utils {
  public static void copyNonNullProperties(Object src, Object target) {
    BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
  }

  public static String[] getNullPropertyNames(Object source) {
    List<String> nullValuePropertyNames = new ArrayList<>();
    for (Field f : source.getClass().getDeclaredFields()) {
      try {
        if (f.get(source) == null) {
          nullValuePropertyNames.add(f.getName());
        }
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return nullValuePropertyNames.toArray(new String[0]);
  }
}
