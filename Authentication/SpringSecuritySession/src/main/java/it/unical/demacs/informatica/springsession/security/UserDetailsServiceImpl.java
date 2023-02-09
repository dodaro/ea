package it.unical.demacs.informatica.springsession.security;

import it.unical.demacs.informatica.springsession.domain.UserAccount;
import it.unical.demacs.informatica.springsession.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if("admin".equals(username))
            return new User(username, new BCryptPasswordEncoder(12).encode("strongPassword"), List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        UserAccount userAccount = userRepository.findByUsername(username);
        if(userAccount != null) return new User(username, userAccount.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        throw new UsernameNotFoundException("User not found");
    }
}
