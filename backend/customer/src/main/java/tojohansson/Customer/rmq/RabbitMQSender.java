package tojohansson.customer.rmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tojohansson.customer.dto.CustomerDto;

@Component
public class RabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void sendCustomerData(CustomerDto dto) {

        try {

            String jsonDto = objectMapper.writeValueAsString(dto);
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "customer.info.response", jsonDto);

        } catch (JsonProcessingException e) {
            // Hantera fel vid JSON-konvertering
            e.printStackTrace();
            // Alternativt, logga eller reagera på fel på ett annat sätt
        }
    }
}
