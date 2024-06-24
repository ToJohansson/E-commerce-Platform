package tojohansson.Product.rmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tojohansson.Product.dto.ProductDto;
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
    public void receiveProductIds(String message)  {
        try {
            ProductDto productDto = objectMapper.readValue(message, ProductDto.class);
            // Long id = (Long) messageConverter.fromMessage(message);

            System.out.println("RECEIVED PRODUCT ID " + productDto.getId());
            // Check if product exists
            if (productService.productExists(productDto.getId())) {
                Long id = productDto.getId();
                int quantity = productDto.getStock();
                ProductDto sendProduct = productService.decreaseProductStock(id, quantity);

                // Send ProductDto back to Order microservice
                rabbitMQSender.sendProductEntity(sendProduct);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
