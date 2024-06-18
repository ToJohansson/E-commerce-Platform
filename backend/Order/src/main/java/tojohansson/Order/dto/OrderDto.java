package tojohansson.Order.dto;


import tojohansson.Order.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {


    // order
    private Long id;
    private double totalPrice;
    private Order.OrderStatus status;

    private List<Long> productIds;
    // product
    private Long productId;
    private String productName;
    private int productStock;
    private double productPrice;

    // customer
    private Long CustomerId;
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerMail;

    // product
    private List<ProductInfoDto> listOfProducts = new ArrayList<>();

    // customer
    private CustomerInfoDto customerInfoDto;

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

    public List<ProductInfoDto> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<ProductInfoDto> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public CustomerInfoDto getCustomerInfoDto() {
        return customerInfoDto;
    }

    public void setCustomerInfoDto(CustomerInfoDto customerInfoDto) {
        this.customerInfoDto = customerInfoDto;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Long getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Long cusomerId) {
        CustomerId = cusomerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCustomerMail() {
        return CustomerMail;
    }

    public void setCustomerMail(String customerMail) {
        CustomerMail = customerMail;
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
