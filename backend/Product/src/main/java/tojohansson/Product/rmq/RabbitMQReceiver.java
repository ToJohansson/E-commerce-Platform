package tojohansson.Product.rmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tojohansson.Product.dto.ProductDto;
import tojohansson.Product.services.ProductService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;

@Component
public class RabbitMQReceiver {

    @Autowired
    private ProductService productService;
    @Autowired
    private RabbitMQSender rabbitMQSender;
    @Autowired
    private MessageConverter messageConverter;

    @RabbitListener(queues = RabbitMQConfig.PRODUCT_INFO_REQUEST_QUEUE)
    public void receiveProductIds(Message message) {
        // Convert message to ProductDto using messageConverter
        Long id = (Long) messageConverter.fromMessage(message);

        System.out.println("Received product ID: " + id);

        ProductDto dto;
        // Check if product exists
        if (productService.productExists(id)) {
            dto = productService.getProductById(id);

            // Send ProductDto back to Order microservice
            rabbitMQSender.sendProductEntity(dto);
        }
    }
}
