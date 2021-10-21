package com.api.inventory.dtos;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  private Integer id;

  @NotEmpty(message = "O login não foi informado")
  private String login;

  @NotEmpty(message = "A senha não foi informada")
  private String password;
}
