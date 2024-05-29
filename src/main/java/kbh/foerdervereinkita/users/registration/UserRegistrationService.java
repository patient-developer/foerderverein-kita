package kbh.foerdervereinkita.users.registration;

import jakarta.transaction.Transactional;
import kbh.foerdervereinkita.storage.repository.UserRepository;
import kbh.foerdervereinkita.users.UserDTO;
import kbh.foerdervereinkita.users.exception.UserException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

  private final UserRepository repository;
  private final UserRegistrationMapper mapper;

  @Transactional
  public void newUserAccount(@NonNull UserDTO user) throws UserException {

    if (repository.existsByName(user.name())) {
      throw UserException.alreadyExists(user.name());
    }

    var entity = mapper.toEntity(user);
    repository.save(entity);
  }

  public boolean userExists(String name) {
    return repository.existsByName(name);
  }
}
