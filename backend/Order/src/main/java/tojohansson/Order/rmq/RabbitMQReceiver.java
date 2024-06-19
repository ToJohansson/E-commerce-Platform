package tojohansson.Order.rmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tojohansson.Order.dto.ProductDto;

import java.io.IOException;

@Component
public class RabbitMQReceiver {

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.PRODUCT_INFO_RESPONSE_QUEUE)
    public void receiveProductEntity(String jsonMessage) {
        try {
            // Deserialize JSON-strängen till ProductDto med ObjectMapper
            ProductDto productDto = objectMapper.readValue(jsonMessage, ProductDto.class);

            System.out.println("Received product: " + productDto.getProductName() + ", Price: " + productDto.getProductPrice());

            // Implementera logik med productDto

        } catch (IOException e) {
            // Hantera fel vid deserialisering
            System.err.println("Failed to deserialize message: " + e.getMessage());
        }
    }
}
