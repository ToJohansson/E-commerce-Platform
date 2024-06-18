package tojohansson.Order.models;

import jakarta.persistence.*;


@Entity
@Table(name = "orders")
public class Order {

    public enum OrderStatus {
        PENDING,
        ACTIVE,
        SHIPPING
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String productName;
    private int productStock;
    private double productPrice;

    private Long CustomerId;
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerMail;

    private double totalPrice;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}

