package com.api.inventory.commons;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response {
  @JsonInclude
  private Object data;

  private Integer code;
  private Date dateTime;
  private String message;
  private Map<String, String> errors;

  public Map<String, String> getErrors() {
    return errors;
  }

  public void setErrors(BindingResult bindingResult) {
    this.errors = new HashMap<>();
    bindingResult.getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      this.errors.put(fieldName, errorMessage);
    });
  }
}
