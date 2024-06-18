package tojohansson.Order.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tojohansson.Order.dto.OrderDto;
import tojohansson.Order.exceptions.EntityNotFoundException;
import tojohansson.Order.models.Order;
import tojohansson.Order.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderService() {
    }

    // Create
    public Order createOrder (OrderDto dto) {
        Order order = mapDtoToOrder(dto, new Order());
        return orderRepository.save(order);
    }

    // Get
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::mapOrderToDto)
                .collect(Collectors.toList());
    }

    public OrderDto orderById(Long id) {
        Order order = checkOrderById(id);
        return mapOrderToDto(order);
    }

    // Put
    public Order updateOrder(Long id, OrderDto dto) {
        Order order = checkOrderById(id);
        order = mapDtoToOrder(dto, order);
        return orderRepository.save(order);
    }

    // Delete
    public void deleteOrder(Long id) {
        Order order = checkOrderById(id);
        orderRepository.deleteById(order.getId());
    }

    // exception
    private Order checkOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Order with ID [" + "] not found."));
    }

    // DTO mapping
    private OrderDto mapOrderToDto(Order order) {
        OrderDto dto = new OrderDto();
        // order
        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setTotalPrice(order.getTotalPrice());
        // customer
        dto.setCustomerId(order.getCustomerId());
        dto.setCustomerName(order.getCustomerName());
        dto.setCustomerAddress(order.getCustomerAddress());
        dto.setCustomerMail(order.getCustomerMail());
        // product
        dto.setProductId(order.getProductId());
        dto.setProductName(order.getProductName());
        dto.setProductPrice(order.getProductPrice());
        dto.setProductStock(order.getProductStock());

        return dto;
    }

    private Order mapDtoToOrder(OrderDto dto, Order order) {
        // order
        order.setStatus(dto.getStatus());
        order.setTotalPrice(dto.getTotalPrice());
        // customer
        order.setCustomerId(dto.getCustomerId());
        order.setCustomerName(dto.getCustomerName());
        order.setCustomerAddress(dto.getCustomerAddress());
        order.setCustomerMail(dto.getCustomerMail());
        //product
        order.setProductId(dto.getProductId());
        order.setProductName(dto.getProductName());
        order.setProductPrice(dto.getProductPrice());
        order.setProductStock(dto.getProductStock());

        return order;
    }
}
