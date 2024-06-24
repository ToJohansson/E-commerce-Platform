package tojohansson.Order.rmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tojohansson.Order.dto.OrderItemDto;
import tojohansson.Order.dto.ProductDto;
import tojohansson.Order.models.Order;
import tojohansson.Order.models.OrderItem;

@Component
public class RabbitMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void sendProductDto(OrderItem dto) throws JsonProcessingException {
        System.out.println("incoming dto " + dto.getProductId());
        ProductDto productDto = mapOrderItemToProduct(dto);

        String jsonDto = objectMapper.writeValueAsString(productDto);

        System.out.println("SENDING PRODUCT ID " + jsonDto);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "product.info.request", jsonDto);

    }

    public void sendCustomerId(Long id) {
        System.out.println("SENDING CUSTOMER ID " + id);

        if (id != null) {
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "customer.info.request", id);
        }
    }

    private ProductDto mapOrderItemToProduct(OrderItem dto) {
        if (dto == null) {
            return null;
        }
        ProductDto productDto = new ProductDto();
        productDto.setId(dto.getProductId());
        productDto.setStock(dto.getQuantity());
        // default
        productDto.setName("default");
        productDto.setPrice(0);
        productDto.setDescription("default");

        return productDto;
    }
}
