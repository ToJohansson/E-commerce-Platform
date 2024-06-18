package tojohansson.Product.rmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMessageReceiver {

    @RabbitListener(queues = "${RabbitMQConfig.PRODUCT_INFO_REQUEST_QUEUE}")
    public void receiveProductIds(Long productId) {
        // Process the received product IDs
        // Example: Print received product IDs
        System.out.println("Received product IDs: " + productId);
        // Implement your logic to fetch products based on these IDs
    }
}