package tojohansson.Order.services;

import org.springframework.stereotype.Service;
import tojohansson.Order.dto.CustomerDto;
import tojohansson.Order.dto.ProductDto;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class DataService {

    private CustomerDto customerDto;
    private final ConcurrentMap<Long, ProductDto> productMap = new ConcurrentHashMap<>();

    public synchronized void setCustomerData(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public synchronized CustomerDto getCustomerData() {
        return customerDto;
    }

    public void addProductData(Long productId, ProductDto productDto) {
        productMap.put(productId, productDto);
    }

    public ProductDto getProductData(Long productId) {
        return productMap.get(productId);
    }

    public ConcurrentMap<Long, ProductDto> getAllProductData() {
        return productMap;
    }
}

