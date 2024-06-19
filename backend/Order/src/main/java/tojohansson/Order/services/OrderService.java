package tojohansson.Order.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tojohansson.Order.rmq.RabbitMQSender;
import tojohansson.Order.dto.OrderDto;
import tojohansson.Order.exceptions.EntityNotFoundException;
import tojohansson.Order.models.Order;
import tojohansson.Order.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private RabbitMQSender rabbitMQSender;
    @Autowired
    private OrderRepository orderRepository;

    public OrderService() {
    }

    // Create
    public Order createOrder(OrderDto dto) {
        Order order = mapDtoToOrder(dto, new Order());

        // ask rmq for data about these product id´s
        rabbitMQSender.sendProductIds(order.getProductIds());


        order.setStatus(Order.OrderStatus.valueOf("PENDING"));
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
                new EntityNotFoundException("Order with ID [" + id + "], not found."));
    }

    // DTO mapping
    private OrderDto mapOrderToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setCustomerId(order.getCustomerId());
        dto.setProductIds(order.getProductIds());
        return dto;
    }

    private Order mapDtoToOrder(OrderDto dto, Order order) {
        order.setStatus(dto.getStatus());
        order.setTotalPrice(dto.getTotalPrice());
        order.setCustomerId(dto.getCustomerId());
        for (Long id : dto.getProductIds()) {

            order.addProductIdToList(id);
        }

        return order;
    }
}
