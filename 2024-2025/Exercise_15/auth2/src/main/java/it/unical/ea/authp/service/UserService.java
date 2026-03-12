package it.unical.ea.authp.service;

import it.unical.ea.authp.dto.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private List<User> mockedUsers;

  @PostConstruct
  private void loadMockedUsers() {
    mockedUsers = new ArrayList<>();
    mockedUsers.add(createAdminUser());
    mockedUsers.add(createBasicUser());
  }

  public  Optional<User> findByEmail(final String email) {
    return mockedUsers.stream().filter(p->p.getEmail().equals(email)).findFirst();
  }

  private User createAdminUser() {
    User user = new User();
    user.setId(1L);
    user.setEmail("admin@uni.it");
    user.setRoles("ADMIN");
    return user;
  }

  private User createBasicUser() {
    User user = new User();
    user.setId(1L);
    user.setEmail("basic@uni.it");
    user.setRoles("BASIC");
    return user;
  }

  public List<User> getAll() {
    return mockedUsers;
  }

}
