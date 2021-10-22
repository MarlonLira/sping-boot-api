package com.api.inventory.dtos.User;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

  @NotBlank(message = "O login não foi informado")
  private String login;

  @NotBlank(message = "A senha não foi informada")
  private String password;
}
