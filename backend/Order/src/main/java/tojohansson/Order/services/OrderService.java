package tojohansson.Order.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tojohansson.Order.dto.CustomerDto;
import tojohansson.Order.dto.OrderItemDto;
import tojohansson.Order.dto.ProductDto;
import tojohansson.Order.models.OrderItem;
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
    @Transactional
    public Order createOrder(OrderDto dto) {
        Order order = mapDtoToOrder(dto, new Order());
        // ask rmq for data about customer
        rabbitMQSender.sendCustomerId(order.getCustomerId());

        // ask rmq for data about these product id´s
        order.getListOfProducts().forEach(product -> rabbitMQSender.sendProductIds(product.getId()));

        order.setStatus(Order.OrderStatus.PENDING);
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
    @Transactional
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
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus());

        dto.setCustomerId(order.getCustomerId());

        List<OrderItemDto> orderItemDtos = order.getListOfProducts().stream()
                .map(this::mapOrderItemToDto)
                .collect(Collectors.toList());
        dto.setListOfProducts(orderItemDtos);

        return dto;
    }

    private Order mapDtoToOrder(OrderDto dto, Order order) {
        Order finalOrder = (order == null) ? new Order() : order;

        finalOrder.setTotalPrice(dto.getTotalPrice());
        finalOrder.setStatus(dto.getStatus());
        finalOrder.setCustomerId(dto.getCustomerId());

        // Map order items
        List<OrderItem> orderItems = dto.getListOfProducts().stream()
                .map(item -> mapDtoToOrderItem(item, finalOrder))
                .collect(Collectors.toList());

        finalOrder.setListOfProducts(orderItems); // Set the list of OrderItems in Order

        return finalOrder;
    }




    private OrderItemDto mapOrderItemToDto(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }
        OrderItemDto dto = new OrderItemDto();
        dto.setId(orderItem.getId());
        dto.setProductId(orderItem.getProductId());
        dto.setQuantity(orderItem.getQuantity());
        return dto;
    }

    private OrderItem mapDtoToOrderItem(OrderItemDto dto, Order order) {
        if (dto == null) {
            return null;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(dto.getProductId());
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setOrder(order); // Set parent order
        return orderItem;
    }
}
