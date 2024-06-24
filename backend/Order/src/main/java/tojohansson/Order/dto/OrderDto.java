package tojohansson.Order.dto;

import tojohansson.Order.models.Order;
import tojohansson.Order.models.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private Long id;
    private double totalPrice;
    private Order.OrderStatus status;
    private Long customerId;
    private List<OrderItemDto> listOfProducts = new ArrayList<>();

    public OrderDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(Order.OrderStatus status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemDto> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<OrderItemDto> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }
}
