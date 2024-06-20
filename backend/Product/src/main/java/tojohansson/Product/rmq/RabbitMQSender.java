package tojohansson.Product.rmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tojohansson.Product.dto.ProductDto;

@Component
public class RabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendProductEntity(ProductDto dto) {
        try {
            // Konvertera ProductDto till JSON-sträng
            String jsonDto = objectMapper.writeValueAsString(dto);
            // Skicka JSON-strängen via RabbitMQ
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "product.info.response", jsonDto);
        } catch (JsonProcessingException e) {
            // Hantera fel vid JSON-konvertering
            e.printStackTrace();
            // Alternativt, logga eller reagera på fel på ett annat sätt
        }
    }
}
