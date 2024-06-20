package tojohansson.Order.dto;


import tojohansson.Order.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {


    // order
    private Long id;
    private double totalPrice;
    private Order.OrderStatus status;

    // customer
    private Long customerId;

    // product
    private List<ProductDto> listOfProducts = new ArrayList<>();
    private List<Long> productIds;

    public OrderDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
}
