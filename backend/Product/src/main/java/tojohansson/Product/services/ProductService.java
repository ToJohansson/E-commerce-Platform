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


    public ProductService() {
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::mapProductToDto)
                .collect(Collectors.toList());
    }

    public void createProduct(ProductDto productDto) {
        Product product = mapDtoToProduct(productDto);
        productRepository.save(product);
    }

    public void updateProduct(ProductDto productDto) {
        Product existingProduct = checkProductById(productDto.getId());
        Product updatedProduct = mapDtoToProduct(productDto);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());
        productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id){
        Product p = checkProductById(id);
        productRepository.deleteById(p.getId());
    }

    public Product checkProductById(Long id) {
        return EntityNotFoundException.checkNotFound(
                productRepository.findById(id),
                "Product not found with id: " + id
        );
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

    private Product mapDtoToProduct(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        return product;
    }
}
