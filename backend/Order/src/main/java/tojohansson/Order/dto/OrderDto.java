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
    private Long CustomerId;
    private CustomerInfoDto customerInfoDto;

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

    public List<ProductDto> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<ProductDto> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public CustomerInfoDto getCustomerInfoDto() {
        return customerInfoDto;
    }

    public void setCustomerInfoDto(CustomerInfoDto customerInfoDto) {
        this.customerInfoDto = customerInfoDto;
    }

    public Long getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Long cusomerId) {
        CustomerId = cusomerId;
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
