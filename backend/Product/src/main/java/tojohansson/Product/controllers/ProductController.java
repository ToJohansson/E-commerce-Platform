package tojohansson.Product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tojohansson.Product.dto.ProductDto;
import tojohansson.Product.models.Product;
import tojohansson.Product.services.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController() {
    }
    @GetMapping("/")
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> productById(@PathVariable("id") Long id){
        ProductDto dto = productService.getProductById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto dto){
        return new ResponseEntity<>(productService.createProduct(dto),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto dto) {
        dto.setId(id); // Ensure ID consistency
        Product updatedProduct = productService.updateProduct(dto);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
