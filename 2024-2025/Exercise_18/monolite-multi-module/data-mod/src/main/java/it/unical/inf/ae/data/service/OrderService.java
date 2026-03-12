package it.unical.inf.ae.data.service;

import it.unical.inf.ae.shared.dto.OrderDto;
import it.unical.inf.ae.shared.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OrderService {
    
    OrderDto createOrder(OrderDto orderDto);
    
    OrderDto getOrderById(Long id);
    
    OrderDto getOrderByOrderNumber(String orderNumber);
    
    OrderDto updateOrderStatus(Long id, OrderStatus status);
    
    void deleteOrder(Long id);
    
    Page<OrderDto> getAllOrders(Pageable pageable);
    
    Page<OrderDto> getOrdersByUserId(Long userId, Pageable pageable);
    
    Page<OrderDto> getOrdersByStatus(OrderStatus status, Pageable pageable);
    
    Page<OrderDto> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    
    BigDecimal getTotalAmountByUserAndStatus(Long userId, OrderStatus status);
    
    long getOrderCountByStatus(OrderStatus status);
    
    String generateOrderNumber();
}