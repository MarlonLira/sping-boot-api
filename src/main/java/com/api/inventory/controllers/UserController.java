package com.api.inventory.controllers;

import javax.validation.Valid;

import com.api.inventory.commons.MessageBundle;
import com.api.inventory.commons.Response;
import com.api.inventory.dtos.User.UserDTO;
import com.api.inventory.dtos.User.UserUpdateDTO;
import com.api.inventory.services.interfaces.IUserService;

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

  public UserController(IUserService service) {
    super(service);
  }

  @GetMapping("/")
  public ResponseEntity<Response> getAll() {
    return Ok(_service.getAll());
  }

  @PostMapping("/")
  public ResponseEntity<Response> save(@RequestBody @Valid UserDTO dto) {
    return Ok(this._service.save(dto), MessageBundle.ACCOUNT_CREATED, HttpStatus.CREATED);
  }

  @PutMapping("/")
  public ResponseEntity<Response> update(@RequestBody @Valid UserUpdateDTO dto) {
    this._service.update(dto);
    return Ok(MessageBundle.UPDATED_SUCCESSFULLY);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Response> delete(@PathVariable Integer id) {
    if (id <= 0) {
      return BadRequest(MessageBundle.PROVIDED_PARAMETERS_NOT_VALID);
    }

    this._service.delete(id);
    return Ok(MessageBundle.DELETED_SUCCESSFULLY);
  }
}
