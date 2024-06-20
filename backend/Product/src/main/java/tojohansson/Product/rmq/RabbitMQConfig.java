package tojohansson.Product.rmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "ecommerce";
    public static final String PRODUCT_INFO_REQUEST_QUEUE = "product.info.request.queue";
    public static final String PRODUCT_INFO_RESPONSE_QUEUE = "product.info.response.queue";

    // Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // Queues
    @Bean
    public Queue productInfoRequestQueue() {
        return new Queue(PRODUCT_INFO_REQUEST_QUEUE, false);
    }
    @Bean
    Queue productInfoResponseQueue() {
        return new Queue(PRODUCT_INFO_RESPONSE_QUEUE, false);
    }

    // Bindings
    @Bean
    public Binding productInfoRequestBinding(Queue productInfoRequestQueue, TopicExchange exchange) {
        return BindingBuilder.bind(productInfoRequestQueue).to(exchange).with("product.info.request");
    }
    @Bean
    Binding productInfoResponseBinding(Queue productInfoResponseQueue, TopicExchange exchange) {
        return BindingBuilder.bind(productInfoResponseQueue).to(exchange).with("product.info.response");
    }

    // Convert
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
