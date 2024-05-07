package it.unical.inf.ea.auth.config.security;

import it.unical.inf.ea.auth.dto.User;
import it.unical.inf.ea.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoggedUserDetailsService implements UserDetailsService {

  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND"));

    LoggedUserDetails loggedUserDetails = new LoggedUserDetails(user);
    log.info(loggedUserDetails.toString());
    return loggedUserDetails;
  }

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
}
