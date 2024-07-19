package tojohansson.Product.rmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tojohansson.Product.dto.ProductDto;
import tojohansson.Product.exceptions.InsufficientStockException;
import tojohansson.Product.services.ProductService;

@Component
public class RabbitMQReceiver {

    @Autowired
    private ProductService productService;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.PRODUCT_INFO_REQUEST_QUEUE)
    public void receiveProductIds(String message) {
        try {
            ProductDto productDto = objectMapper.readValue(message, ProductDto.class);

            if (productService.productExists(productDto.getId())) {
                Long id = productDto.getId();
                int requestedQuantity = productDto.getStock();
                ProductDto responseProduct = null;
                try {
                    responseProduct = productService.decreaseProductStock(id, requestedQuantity);
                    responseProduct.setStock(requestedQuantity);
                } catch (InsufficientStockException e) {
                    responseProduct = new ProductDto();
                    responseProduct.setId(id);
                    responseProduct.setStock(0);
                    responseProduct.setName("Not enough stock");
                }
                rabbitMQSender.sendProductEntity(responseProduct);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

