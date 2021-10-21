package com.api.inventory.controllers;

import javax.validation.Valid;

import com.api.inventory.commons.Response;
import com.api.inventory.dtos.UserDTO;
import com.api.inventory.models.UserModel;
import com.api.inventory.services.interfaces.IUserService;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<IUserService> {

  public UserController(IUserService service, ModelMapper mapper) {
    super(service, mapper);
  }

  @GetMapping("/")
  public ResponseEntity<Response> getAll() {
    return Ok(_service.getAll());
  }

  @PostMapping("/")
  public ResponseEntity<Response> save(@RequestBody @Valid UserDTO dto) {
    return Ok(this._service.save(_mapper.map(dto, UserModel.class)), HttpStatus.CREATED);
  }

  @PutMapping("/")
  public ResponseEntity<Response> update(@RequestBody @Valid UserDTO dto) {
    this._service.update(_mapper.map(dto, UserModel.class));
    return Ok();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Response> delete(@PathVariable Integer id) {
    if (id <= 0) {
      return BadRequest("O id informado não é válido");
    }

    this._service.delete(id);
    return Ok();
  }
}
