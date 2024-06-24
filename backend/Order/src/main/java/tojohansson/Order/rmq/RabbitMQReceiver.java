package tojohansson.Order.rmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tojohansson.Order.dto.CustomerDto;
import tojohansson.Order.dto.ProductDto;

import java.io.IOException;

@Component
public class RabbitMQReceiver {

    @Autowired
    private ObjectMapper objectMapper;


    @RabbitListener(queues = RabbitMQConfig.CUSTOMER_INFO_RESPONSE_QUEUE)
    public void receiveCustomerData(String jsonMessage) {
        try {
            // Deserialize JSON-strängen till ProductDto med ObjectMapper
            CustomerDto dto = objectMapper.readValue(jsonMessage, CustomerDto.class);

            // Implementera logik med Dto
            System.out.println("RECEIVING CUSTOMER " + dto.getName());
        } catch (IOException e) {
            // Hantera fel vid deserialisering
            System.err.println("Failed to deserialize message: " + e.getMessage());
        }
    }
    @RabbitListener(queues = RabbitMQConfig.PRODUCT_INFO_RESPONSE_QUEUE)
    public void receiveProductData(String jsonMessage) {
        try {
            // Deserialize JSON-strängen till Dto med ObjectMapper
            ProductDto dto = objectMapper.readValue(jsonMessage, ProductDto.class);

            // Implementera logik med productDto
            System.out.println("RECEIVING PRODUCT " + dto.getName());

        } catch (IOException e) {
            // Hantera fel vid deserialisering
            System.err.println("Failed to deserialize message: " + e.getMessage());
        }
    }
}
