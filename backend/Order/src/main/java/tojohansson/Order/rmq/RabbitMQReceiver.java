package tojohansson.Order.rmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tojohansson.Order.dto.CustomerDto;
import tojohansson.Order.dto.ProductDto;
import tojohansson.Order.services.DataService;

import java.io.IOException;

@Component
public class RabbitMQReceiver {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DataService dataService;

    @RabbitListener(queues = RabbitMQConfig.CUSTOMER_INFO_RESPONSE_QUEUE)
    public void receiveCustomerData(String jsonMessage) {
        try {
            CustomerDto dto = objectMapper.readValue(jsonMessage, CustomerDto.class);

            dataService.setCustomerData(dto);
        } catch (IOException e) {
            System.err.println("Failed to deserialize message: " + e.getMessage());
        }
    }
    @RabbitListener(queues = RabbitMQConfig.PRODUCT_INFO_RESPONSE_QUEUE)
    public void receiveProductData(String jsonMessage) {
        try {
            ProductDto dto = objectMapper.readValue(jsonMessage, ProductDto.class);

            dataService.addProductData(dto.getId(), dto);

        } catch (IOException e) {
            System.err.println("Failed to deserialize message: " + e.getMessage());
        }
    }
}
