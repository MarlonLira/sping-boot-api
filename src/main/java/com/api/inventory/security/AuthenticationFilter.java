package com.api.inventory.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.api.inventory.models.UserModel;
import com.api.inventory.security.data.UserDetailsData;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager _authenticationManager;

  public AuthenticationFilter(AuthenticationManager authenticationManager) {
    this._authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {
    try {
      UserModel _user = new ObjectMapper().readValue(request.getInputStream(), UserModel.class);

      return _authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(_user.getLogin(), _user.getPassword(), new ArrayList<>()));
    } catch (IOException e) {
      throw new RuntimeException("Falha ao autenticar o usuário", e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authResult) throws IOException, ServletException {

    UserDetailsData _userDetailsData = (UserDetailsData) authResult.getPrincipal();

    super.successfulAuthentication(request, response, chain, authResult);
  }

}
