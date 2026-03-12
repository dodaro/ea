package it.unical.inf.ea.uniprj.data.service;

import it.unical.inf.ea.uniprj.data.dao.UserDao;
import it.unical.inf.ea.uniprj.data.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserDao userDao;

  @Override
  public void save(User user) {
    userDao.save(user);
  }
}
