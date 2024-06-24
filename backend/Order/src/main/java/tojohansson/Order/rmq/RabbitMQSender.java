package tojohansson.Order.rmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RabbitMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendProductIds(Long id) {

            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "product.info.request", id);

    }
    public void sendCustomerId(Long id) {
        if(id != null) {
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "customer.info.request", id);
        }
    }
}
