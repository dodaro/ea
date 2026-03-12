package it.unical.inf.ea.uniprjms.gateway.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
public class LoggedUserToken implements  Serializable {

  private final String username;

  private final String password;

  private final List<String> auths;

}
