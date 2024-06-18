package tojohansson.Order.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tojohansson.Order.models.Order;

@Component
public class RabbitMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendOrderCreatedMessage(Order order) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "order.created", order);
    }
}
