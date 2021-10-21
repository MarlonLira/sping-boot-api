package com.api.inventory.security.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.api.inventory.models.UserModel;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsData implements UserDetails {

  private final Optional<UserModel> _user;

  public UserDetailsData(Optional<UserModel> user) {
    this._user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new ArrayList<>();
  }

  @Override
  public String getPassword() {
    return _user.orElse(new UserModel()).getPassword();
  }

  @Override
  public String getUsername() {
    return _user.orElse(new UserModel()).getLogin();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
