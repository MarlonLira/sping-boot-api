package com.api.inventory.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
  private List<String> errors;

  public List<String> getErrors() {
    if (this.errors == null) {
      this.errors = new ArrayList<>();
    }
    return errors;
  }
}
