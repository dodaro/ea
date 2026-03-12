package it.unical.inf.ae.data.service.impl;

import it.unical.inf.ae.core.mapper.UserMapper;
import it.unical.inf.ae.data.service.UserService;
import it.unical.inf.ae.data.dao.UserRepository;
import it.unical.inf.ae.shared.dto.UserDto;
import it.unical.inf.ae.shared.entity.User;
import it.unical.inf.ae.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Creating new user with username: {}", userDto.getUsername());
        
        if (existsByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + userDto.getUsername());
        }
        
        if (existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + userDto.getEmail());
        }
        
        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        
        log.info("User created successfully with ID: {}", savedUser.getId());
        return userMapper.toDto(savedUser);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return userMapper.toDto(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return userMapper.toDto(user);
    }
    
    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        log.info("Updating user with ID: {}", id);
        
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        
        // Check if username/email conflicts with other users
        if (!existingUser.getUsername().equals(userDto.getUsername()) && 
            existsByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + userDto.getUsername());
        }
        
        if (!existingUser.getEmail().equals(userDto.getEmail()) && 
            existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + userDto.getEmail());
        }
        
        userMapper.updateEntityFromDto(userDto, existingUser);
        User updatedUser = userRepository.save(existingUser);
        
        log.info("User updated successfully with ID: {}", updatedUser.getId());
        return userMapper.toDto(updatedUser);
    }
    
    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with ID: {}", id);
        
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        
        userRepository.deleteById(id);
        log.info("User deleted successfully with ID: {}", id);
    }
    
    @Override
    public void deactivateUser(Long id) {
        log.info("Deactivating user with ID: {}", id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        
        user.setActive(false);
        userRepository.save(user);
        
        log.info("User deactivated successfully with ID: {}", id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> getActiveUsers(Pageable pageable) {
        Page<User> users = userRepository.findByActiveTrue(pageable);
        return users.map(userMapper::toDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> searchUsers(String searchTerm, Pageable pageable) {
        Page<User> users = userRepository.findBySearchTerm(searchTerm, pageable);
        return users.map(userMapper::toDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long getActiveUserCount() {
        return userRepository.countActiveUsers();
    }
}