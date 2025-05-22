package it.unical.inf.ae.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.inf.ae.core.service.OrderService;
import it.unical.inf.ae.shared.dto.OrderDto;
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
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Order Management", description = "API per la gestione degli ordini")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {
    
    private final OrderService orderService;
    
    @PostMapping
    @Operation(summary = "Crea nuovo ordine", description = "Crea un nuovo ordine nel sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ordine creato con successo"),
            @ApiResponse(responseCode = "400", description = "Dati di input non validi"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
    })
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
        log.info("Creating order for user ID: {} with amount: {}", orderDto.getUserId(), orderDto.getTotalAmount());
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Ottieni ordine per ID", description = "Recupera un ordine specifico tramite il suo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordine trovato"),
            @ApiResponse(responseCode = "404", description = "Ordine non trovato")
    })
    public ResponseEntity<OrderDto> getOrderById(@Parameter(description = "ID dell'ordine") @PathVariable Long id) {
        log.info("Getting order by ID: {}", id);
        OrderDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "Ordini per utente", description = "Recupera gli ordini di un utente specifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordini utente recuperati con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
    })
    public ResponseEntity<Page<OrderDto>> getOrdersByUserId(
            @Parameter(description = "ID dell'utente") @PathVariable Long userId,
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        log.info("Getting orders for user ID: {} with pagination: {}", userId, pageable);
        Page<OrderDto> orders = orderService.getOrdersByUserId(userId, pageable);
        return ResponseEntity.ok(orders);
    }
}