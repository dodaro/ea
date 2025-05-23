package it.unical.inf.ae.data.service;

import it.unical.inf.ae.shared.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    
    UserDto createUser(UserDto userDto);
    
    UserDto getUserById(Long id);
    
    UserDto getUserByUsername(String username);
    
    UserDto updateUser(Long id, UserDto userDto);
    
    void deleteUser(Long id);
    
    void deactivateUser(Long id);
    
    Page<UserDto> getAllUsers(Pageable pageable);
    
    Page<UserDto> getActiveUsers(Pageable pageable);
    
    Page<UserDto> searchUsers(String searchTerm, Pageable pageable);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
    long getActiveUserCount();
}