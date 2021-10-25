package com.api.inventory.security;

import com.api.inventory.models.UserModel;
import com.api.inventory.security.data.UserDetailsData;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.util.UrlPathHelper;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  public static final int TOKEN_EXPIRE_AT = 600_00;
  public static final String TOKEN_PASSWORD = "29577d61-d4ec-4511-aede-53779ba1119b";
  private final static UrlPathHelper _urlPathHelper = new UrlPathHelper();
  private static Logger _logger = LoggerFactory.getLogger(AuthenticationFilter.class);
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
      _logger.error("Falha ao autenticar o usuário!", e);
      throw new RuntimeException("Falha ao autenticar o usuário", e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authResult) throws IOException, ServletException {
    UserDetailsData _userDetailsData = (UserDetailsData) authResult.getPrincipal();

    String _token = JWT.create().withSubject(_userDetailsData.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRE_AT)).sign(Algorithm.HMAC512(TOKEN_PASSWORD));

    response.getWriter().write(_token);
    response.getWriter().flush();
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException failed) throws IOException, ServletException {
    var _path = _urlPathHelper.getPathWithinApplication((HttpServletRequest) request);
    _logger.debug("Autenticação falhou ao tentar acessar: " + _path);
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "A autenticação falhou");
  }
}
