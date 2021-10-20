package com.api.inventory.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class ValidationFilter extends BasicAuthenticationFilter {
  public static final String HEADER_ATTRIBUTE = "Authorization";
  public static final String ATTRIBUTE_PREFIX = "Bearer ";

  public ValidationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain chain
  )
    throws IOException, ServletException {
    String _attribute = request.getHeader(HEADER_ATTRIBUTE);

    if (_attribute == null || !_attribute.startsWith(ATTRIBUTE_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }

    String _token = _attribute.replace(ATTRIBUTE_PREFIX, "");
    UsernamePasswordAuthenticationToken _authenticationToken = getAuthenticationToken(
      _token
    );
    SecurityContextHolder.getContext().setAuthentication(_authenticationToken);
    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthenticationToken(
    String token
  ) {
    String _user = JWT
      .require(Algorithm.HMAC512(AuthenticationFilter.TOKEN_PASSWORD))
      .build()
      .verify(token)
      .getSubject();

    if (_user == null) {
      return null;
    }

    return new UsernamePasswordAuthenticationToken(
      _user,
      null,
      new ArrayList<>()
    );
  }
}
