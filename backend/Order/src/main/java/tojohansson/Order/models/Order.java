package tojohansson.Order.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {

    public enum OrderStatus {
        PENDING,
        ACTIVE,
        SHIPPING,
        CANCELLED
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalPrice;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // Customer
    private Long customerId;
    // Product
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> listOfProducts = new ArrayList<>();

    public Order(){}


    public Long getId() {
        return id;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderItem> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<OrderItem> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }
}

