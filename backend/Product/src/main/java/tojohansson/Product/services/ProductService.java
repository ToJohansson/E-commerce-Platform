package tojohansson.Product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tojohansson.Product.dto.ProductDto;
import tojohansson.Product.exceptions.EntityNotFoundException;
import tojohansson.Product.models.Product;
import tojohansson.Product.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::mapProductToDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        Product product = checkProductById(id);
        return mapProductToDto(product);
    }

    public Product createProduct(ProductDto productDto) {
        Product product = (mapDtoToProduct(productDto, new Product()));
        return productRepository.save(product);
    }

    public Product updateProduct(ProductDto productDto) {
        Product existingProduct = checkProductById(productDto.getId());
        mapDtoToProduct(productDto, existingProduct); // Update existingProduct fields
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        Product product = checkProductById(id);
        productRepository.deleteById(product.getId());
    }

    public Product checkProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    private ProductDto mapProductToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        return dto;
    }

    private Product mapDtoToProduct(ProductDto dto, Product product) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        return product;
    }
}
