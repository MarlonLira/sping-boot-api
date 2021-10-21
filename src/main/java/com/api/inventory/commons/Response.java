package com.api.inventory.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
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
  private LocalDateTime dateTime;
  private String message;
  private Map<String, String> errors;

  public Map<String, String> getErrors() {
    return errors;
  }
}
