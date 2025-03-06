package it.unical.inf.ea.auth.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import it.unical.inf.ea.auth.config.security.JwtUtil;
import it.unical.inf.ea.auth.config.security.LoggedUserDetails;
import it.unical.inf.ea.auth.dto.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

  private final PasswordEncoder passwordEncoder;

  private List<User> mockedUsers;

  @PostConstruct
  private void loadMockedUsers() {
    mockedUsers = new ArrayList<>();
    mockedUsers.add(createAdminUser());
    mockedUsers.add(createBasicUser());
  }

  public void save(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    // TODO > save
  }

  public  Optional<User> findByEmail(final String email) {
    return mockedUsers.stream().filter(p->p.getEmail().equals(email)).findFirst();
  }

  @Transactional(readOnly = true)
  public Map<String,String> refreshToken(String authorizationHeader, String issuer) throws BadJOSEException, ParseException, JOSEException {

    String refreshToken = authorizationHeader.substring("Bearer ".length());
    UsernamePasswordAuthenticationToken authenticationToken = JwtUtil.parseToken(refreshToken);
    String username = authenticationToken.getName();
    User user = findByEmail(username).orElseThrow(()->new RuntimeException("user not found"));
    LoggedUserDetails loggedUserDetails = new LoggedUserDetails(user);
    String accessToken = JwtUtil.createAccessToken(loggedUserDetails.getUsername(),issuer,
        loggedUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
    return Map.of("access_token", accessToken, "refresh_token", refreshToken);
  }

  private User createAdminUser() {
    User user = new User();
    user.setId(1L);
    user.setEmail("admin@uni.it");
    user.setPassword(passwordEncoder.encode("passwd"));
    user.setRoles("ADMIN");
    return user;
  }

  private User createBasicUser() {
    User user = new User();
    user.setId(1L);
    user.setEmail("basic@uni.it");
    user.setPassword(passwordEncoder.encode("passwd"));
    user.setRoles("BASIC");
    return user;
  }

  public List<User> getAll() {
    return mockedUsers;
  }
}
