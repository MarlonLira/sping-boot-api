package com.api.inventory.services;

import com.api.inventory.models.UserModel;
import com.api.inventory.repositories.IUserRepository;
import com.api.inventory.services.interfaces.IUserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

  private final IUserRepository _repository;
  private final PasswordEncoder _encoder;

  @Autowired
  public UserService(IUserRepository repository, PasswordEncoder encoder) {
    this._repository = repository;
    this._encoder = encoder;
  }

  @Override
  public List<UserModel> getAll() {
    return _repository.findAll();
  }

  @Override
  public Integer save(UserModel model) {
    model.setPassword(_encoder.encode(model.getPassword()));
    return _repository.save(model).getId();
  }

  @Override
  public String login(UserModel model) {
    Optional<UserModel> _optUser = this._repository.findByLogin(model.getLogin());
    if (_optUser.isEmpty()) {
      return null;
    }

    UserModel _user = _optUser.get();
    boolean valid = _encoder.matches(model.getPassword(), _user.getPassword());

    if (!valid) {
      return null;
    }

    return "valid";
  }

  @Override
  public void update(UserModel model) {
    if (!model.getPassword().isEmpty()) {
      model.setPassword(_encoder.encode(model.getPassword()));
    }

    _repository.save(model);
  }

  @Override
  public void delete(Integer id) {
    _repository.deleteById(id);
  }
}
