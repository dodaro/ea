package it.unical.inf.ae.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.inf.ae.data.service.UserService;
import it.unical.inf.ae.shared.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Management", description = "API per la gestione degli utenti")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    
    private final UserService userService;
    
    @PostMapping
    @Operation(summary = "Crea nuovo utente", description = "Crea un nuovo utente nel sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Utente creato con successo"),
            @ApiResponse(responseCode = "400", description = "Dati di input non validi"),
            @ApiResponse(responseCode = "409", description = "Username o email già esistenti")
    })
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        log.info("Creating user with username: {}", userDto.getUsername());
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Ottieni utente per ID", description = "Recupera un utente specifico tramite il suo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utente trovato"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
    })
    public ResponseEntity<UserDto> getUserById(@Parameter(description = "ID dell'utente") @PathVariable Long id) {
        log.info("Getting user by ID: {}", id);
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping
    @Operation(summary = "Lista tutti gli utenti", description = "Recupera una lista paginata di tutti gli utenti")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista utenti recuperata con successo")
    })
    public ResponseEntity<Page<UserDto>> getAllUsers(@PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        log.info("Getting all users with pagination: {}", pageable);
        Page<UserDto> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }
}