package tojohansson.customer.rmq;

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
    public static final String CUSTOMER_INFO_REQUEST_QUEUE = "customer.info.request.queue";
    public static final String CUSTOMER_INFO_RESPONSE_QUEUE = "customer.info.response.queue";


    public RabbitMQConfig() {
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue customerInfoRequestQueue() {
        return new Queue(CUSTOMER_INFO_REQUEST_QUEUE, false);
    }
    @Bean
    public Queue customerInfoResponseQueue(){
        return new Queue(CUSTOMER_INFO_RESPONSE_QUEUE, false);
    }

    @Bean
    Binding customerInfoRequestBinding(Queue customerInfoRequestQueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(customerInfoRequestQueue)
                .to(exchange)
                .with("customer.info.request");
    }

    @Bean
    Binding customerInfoResponseBinding(Queue customerInfoResponseQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(customerInfoResponseQueue)
                .to(exchange)
                .with("customer.info.response");
    }
    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
