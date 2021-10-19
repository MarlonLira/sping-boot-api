package com.api.inventory.controllers;

import java.util.List;

import com.api.inventory.models.UserModel;
import com.api.inventory.services.interfaces.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private final IUserService _service;

  @Autowired
  public UserController(IUserService service) {
    this._service = service;
  }

  @GetMapping("/")
  public ResponseEntity<List<UserModel>> getAll() {
    return ResponseEntity.ok(_service.getAll());
  }

  @PostMapping("/")
  public ResponseEntity<Integer> save(@RequestBody UserModel model) {
    return ResponseEntity.ok(_service.save(model));
  }

  @PostMapping("/login")
  public ResponseEntity<String> Login(@RequestBody UserModel model) {
    String _result = this._service.Login(model);
    if (_result == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
    }
    return ResponseEntity.ok(_result);
  }

}
