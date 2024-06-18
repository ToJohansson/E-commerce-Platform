package tojohansson.Product.rmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.AllowedListDeserializingMessageConverter;
import java.util.ArrayList;

@Configuration
public class RabbitMqConfig {
    public static final String EXCHANGE_NAME = "ecommerce";
    public static final String PRODUCT_INFO_REQUEST_QUEUE = "product.info.request.queue";

    @Bean
    public Queue productInfoRequestQueue() {
        return new Queue(PRODUCT_INFO_REQUEST_QUEUE, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding productInfoRequestBinding(Queue productInfoRequestQueue, TopicExchange exchange) {
        return BindingBuilder.bind(productInfoRequestQueue).to(exchange).with("product.info.request");
    }

}
