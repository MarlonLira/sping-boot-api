package com.api.inventory.services;

import java.util.List;
import java.util.Optional;

import com.api.inventory.commons.Utils;
import com.api.inventory.commons.Mapper.Mapper;
import com.api.inventory.dtos.User.UserDTO;
import com.api.inventory.dtos.User.UserUpdateDTO;
import com.api.inventory.models.UserModel;
import com.api.inventory.repositories.IUserRepository;
import com.api.inventory.services.interfaces.IUserService;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.experimental.ExtensionMethod;

@ExtensionMethod({ Mapper.class })
@Service
public class UserService extends BaseService<IUserRepository> implements IUserService {

  private final PasswordEncoder _encoder;

  public UserService(IUserRepository repository, ModelMapper mapper, PasswordEncoder encoder) {
    super(repository, mapper);
    this._encoder = encoder;
  }

  @Override
  public List<UserDTO> getAll() {
    return _mapper.mapList(_repository.findAll(), UserDTO.class);
  }

  @Override
  public Integer save(UserDTO dto) {
    var _model = _mapper.map(dto, UserModel.class);
    _model.setPassword(_encoder.encode(_model.getPassword()));
    return _repository.save(_model).getId();
  }

  @Override
  public String login(UserDTO dto) {
    var _model = _mapper.map(dto, UserModel.class);
    Optional<UserModel> _optUser = this._repository.findByLogin(_model.getLogin());
    if (_optUser.isEmpty()) {
      return null;
    }

    UserModel _user = _optUser.get();
    boolean valid = _encoder.matches(_model.getPassword(), _user.getPassword());

    if (!valid) {
      return null;
    }

    return "valid";
  }

  @Override
  public void update(UserUpdateDTO dto) {
    var _modelOp = _repository.findById(dto.getId());

    if (_modelOp.isEmpty()) {
      return;
    }

    if (!dto.getPassword().isEmpty()) {
      dto.setPassword(_encoder.encode(dto.getPassword()));
    }

    var _model = _modelOp.get();

    Utils.copyNonNullProperties(dto, _model);

    _repository.save(_model);
  }

  @Override
  public void delete(Integer id) {
    _repository.deleteById(id);
  }
}
