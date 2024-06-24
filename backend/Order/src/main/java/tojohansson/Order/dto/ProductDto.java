package tojohansson.Order.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import tojohansson.Order.deserialize.ProductDtoDeserializer;

@JsonDeserialize(using = ProductDtoDeserializer.class)
public class ProductDto  {

    private Long id;
    private String name;
    private int stock;
    private double price;
    private String description;

    public ProductDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
