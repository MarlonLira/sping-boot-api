package com.api.inventory.controllers;

import com.api.inventory.commons.Response;
import com.api.inventory.models.UserModel;
import com.api.inventory.services.interfaces.IUserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
  public ResponseEntity<Response> save(@RequestBody UserModel model) {
    try {
      return Ok(this._service.save(model), HttpStatus.CREATED);
    } catch (Exception except) {
      return BadRequest(except.getMessage());
    }
  }
}
