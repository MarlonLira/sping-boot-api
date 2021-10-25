package com.api.inventory.dtos.User;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {

  @NotNull(message = "O id não foi informada!")
  @Range(min = 1, message = "O id informado não é válido!")
  private Integer id;

  private String login;

  private String password;
}
