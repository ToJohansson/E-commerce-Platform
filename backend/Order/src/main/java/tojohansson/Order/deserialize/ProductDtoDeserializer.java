package tojohansson.Order.deserialize;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import tojohansson.Order.dto.ProductDto;

@Component
public class ProductDtoDeserializer extends JsonDeserializer<ProductDto> {

    @Override
    public ProductDto deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Long id = node.get("id").asLong();
        String name = node.get("name").asText();
        String description = node.get("description").asText();
        double price = node.get("price").asDouble();
        int stock = node.get("stock").asInt();

        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setStock(stock);
        productDto.setPrice(price);

        return productDto;
    }

}
