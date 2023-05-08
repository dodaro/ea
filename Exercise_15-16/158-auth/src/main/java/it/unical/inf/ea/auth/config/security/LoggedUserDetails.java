package it.unical.inf.ea.auth.config.security;

import it.unical.inf.ea.auth.dto.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class LoggedUserDetails implements UserDetails, Serializable {

  private static final long serialVersionUID = 1;

  private String username;

  private String password;

  private List<GrantedAuthority> auths;

  private boolean enabled; //delete

  private boolean notLocked; //active

  public LoggedUserDetails(User user) {
    this.username = user.getEmail();
    this.password = user.getPassword();
    this.enabled = !user.isDeleted();
    this.notLocked = !user.isActive();
    this.auths = Arrays.stream(user.getRoles().split(",")) //
        .map(role -> "ROLE_" + role)// oppure salvare "ROLE_" nel db
        .map(SimpleGrantedAuthority::new) //
        .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return auths;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return notLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
