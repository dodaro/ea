package it.unical.ea.authp.auth;

import it.unical.ea.authp.dao.UserDao;
import it.unical.ea.authp.entities.User;
import it.unical.ea.authp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found."));

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .authorities(user.getRoles().stream().map(role -> "ROLE_" + role).toArray(String[]::new))
            .accountLocked(user.isDeleted())
            .disabled(!user.isActive())
            .build();
    }
}
