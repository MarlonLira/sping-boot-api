package com.api.inventory.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.api.inventory.commons.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseController<TService> {

  protected final TService _service;

  public BaseController(TService service) {
    this._service = service;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }

  protected ResponseEntity<Response> Ok(Object value) {
    var _response = new Response();
    _response.setCode(200);
    _response.setData(value);
    _response.setDateTime(LocalDateTime.now());

    return ResponseEntity.ok(_response);
  }

  protected ResponseEntity<Response> Ok(Object value, HttpStatus status) {
    var _response = new Response();
    _response.setCode(status.value());
    _response.setData(value);
    _response.setDateTime(LocalDateTime.now());

    return ResponseEntity.ok(_response);
  }

  protected ResponseEntity<Response> BadRequest(String message) {
    var _response = new Response();
    _response.setCode(400);
    _response.setMessage(message);
    _response.setDateTime(LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_response);
  }

  protected ResponseEntity<Response> BadRequest(String message, HttpStatus status) {
    var _response = new Response();
    _response.setCode(status.value());
    _response.setMessage(message);
    _response.setDateTime(LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(_response);
  }

}
