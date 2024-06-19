package tojohansson.Order.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import tojohansson.Order.deserialize.ProductDtoDeserializer;

@JsonDeserialize(using = ProductDtoDeserializer.class)
public class ProductDto  {

    private Long productId;
    private String productName;
    private int productStock;
    private double productPrice;

    public ProductDto() {
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
}
