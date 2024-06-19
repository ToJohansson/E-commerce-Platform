package tojohansson.Order.rmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RabbitMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sendProductIds(List<Long> productIds) {
        for (Long id : productIds) {
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "product.info.request", id);
        }
    }
}
