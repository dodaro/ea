package it.unical.inf.ae.data.service.impl;

import it.unical.inf.ae.core.mapper.OrderMapper;
import it.unical.inf.ae.data.service.OrderService;
import it.unical.inf.ae.data.dao.OrderRepository;
import it.unical.inf.ae.data.dao.UserRepository;
import it.unical.inf.ae.shared.dto.OrderDto;
import it.unical.inf.ae.shared.entity.Order;
import it.unical.inf.ae.shared.entity.OrderStatus;
import it.unical.inf.ae.shared.entity.User;
import it.unical.inf.ae.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    
    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        log.info("Creating new order for user ID: {}", orderDto.getUserId());
        
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", orderDto.getUserId()));
        
        Order order = orderMapper.toEntity(orderDto);
        order.setUser(user);
        
        if (order.getOrderNumber() == null || order.getOrderNumber().isEmpty()) {
            order.setOrderNumber(generateOrderNumber());
        }
        
        Order savedOrder = orderRepository.save(order);
        
        log.info("Order created successfully with ID: {} and order number: {}", 
                savedOrder.getId(), savedOrder.getOrderNumber());
        return orderMapper.toDto(savedOrder);
    }
    
    @Override
    @Transactional(readOnly = true)
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findByIdWithUser(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        return orderMapper.toDto(order);
    }
    
    @Override
    @Transactional(readOnly = true)
    public OrderDto getOrderByOrderNumber(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "orderNumber", orderNumber));
        return orderMapper.toDto(order);
    }
    
    @Override
    public OrderDto updateOrderStatus(Long id, OrderStatus status) {
        log.info("Updating order status for ID: {} to {}", id, status);
        
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        
        log.info("Order status updated successfully for ID: {}", id);
        return orderMapper.toDto(updatedOrder);
    }
    
    @Override
    public void deleteOrder(Long id) {
        log.info("Deleting order with ID: {}", id);
        
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order", "id", id);
        }
        
        orderRepository.deleteById(id);
        log.info("Order deleted successfully with ID: {}", id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<OrderDto> getAllOrders(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders.map(orderMapper::toDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<OrderDto> getOrdersByUserId(Long userId, Pageable pageable) {
        // Verifica che l'utente esista
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "id", userId);
        }
        
        Page<Order> orders = orderRepository.findByUserId(userId, pageable);
        return orders.map(orderMapper::toDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<OrderDto> getOrdersByStatus(OrderStatus status, Pageable pageable) {
        Page<Order> orders = orderRepository.findByStatus(status, pageable);
        return orders.map(orderMapper::toDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<OrderDto> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        Page<Order> orders = orderRepository.findByDateRange(startDate, endDate, pageable);
        return orders.map(orderMapper::toDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTotalAmountByUserAndStatus(Long userId, OrderStatus status) {
        // Verifica che l'utente esista
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "id", userId);
        }
        
        BigDecimal total = orderRepository.getTotalAmountByUserAndStatus(userId, status);
        return total != null ? total : BigDecimal.ZERO;
    }
    
    @Override
    @Transactional(readOnly = true)
    public long getOrderCountByStatus(OrderStatus status) {
        return orderRepository.countByStatus(status);
    }
    
    @Override
    public String generateOrderNumber() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String uuid = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return "ORD-" + timestamp + "-" + uuid;
    }
}