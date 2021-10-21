package com.api.inventory.services;

import com.api.inventory.models.UserModel;
import com.api.inventory.repositories.IUserRepository;
import com.api.inventory.security.data.UserDetailsData;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsDataService implements UserDetailsService {

  private final IUserRepository _repository;

  public UserDetailsDataService(IUserRepository repository) {
    this._repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    Optional<UserModel> _user = _repository.findByLogin(username);

    if (_user.isEmpty()) {
      throw new UsernameNotFoundException(
        "Usuário [" + username + "] não encontrado!"
      );
    }

    return new UserDetailsData(_user);
  }
}
