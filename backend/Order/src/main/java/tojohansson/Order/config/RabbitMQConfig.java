package tojohansson.Order.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "ecommerce";
    public static final String PRODUCT_INFO_REQUEST_QUEUE = "product.info.request.queue";
    public static final String CUSTOMER_INFO_REQUEST_QUEUE = "customer.info.request.queue";
    public static final String PRODUCT_INFO_RESPONSE_QUEUE = "product.info.response.queue";
    public static final String CUSTOMER_INFO_RESPONSE_QUEUE = "customer.info.response.queue";

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue productInfoRequestQueue() {
        return new Queue(PRODUCT_INFO_REQUEST_QUEUE, false);
    }

    @Bean
    Queue customerInfoRequestQueue() {
        return new Queue(CUSTOMER_INFO_REQUEST_QUEUE, false);
    }

    @Bean
    Queue productInfoResponseQueue() {
        return new Queue(PRODUCT_INFO_RESPONSE_QUEUE, false);
    }

    @Bean
    Queue customerInfoResponseQueue() {
        return new Queue(CUSTOMER_INFO_RESPONSE_QUEUE, false);
    }

    @Bean
    Binding productInfoRequestBinding(Queue productInfoRequestQueue, TopicExchange exchange) {
        return BindingBuilder.bind(productInfoRequestQueue).to(exchange).with("product.info.request");
    }

    @Bean
    Binding customerInfoRequestBinding(Queue customerInfoRequestQueue, TopicExchange exchange) {
        return BindingBuilder.bind(customerInfoRequestQueue).to(exchange).with("customer.info.request");
    }

    @Bean
    Binding productInfoResponseBinding(Queue productInfoResponseQueue, TopicExchange exchange) {
        return BindingBuilder.bind(productInfoResponseQueue).to(exchange).with("product.info.response");
    }

    @Bean
    Binding customerInfoResponseBinding(Queue customerInfoResponseQueue, TopicExchange exchange) {
        return BindingBuilder.bind(customerInfoResponseQueue).to(exchange).with("customer.info.response");
    }
}
