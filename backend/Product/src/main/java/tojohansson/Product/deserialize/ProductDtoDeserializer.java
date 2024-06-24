package tojohansson.Product.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import tojohansson.Product.dto.ProductDto;

import java.io.IOException;

public class ProductDtoDeserializer extends StdDeserializer<ProductDto> {

    public ProductDtoDeserializer() {
        this(null);
    }

    public ProductDtoDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ProductDto deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException{
        JsonNode node = jp.getCodec().readTree(jp);

        // Kontrollera om noderna är null innan du försöker hämta deras värden
        JsonNode idNode = node.get("id");
        Long id = idNode != null && !idNode.isNull() ? idNode.asLong() : null;

        JsonNode nameNode = node.get("name");
        String name = nameNode != null && !nameNode.isNull() ? nameNode.asText() : null;

        JsonNode priceNode = node.get("price");
        double price = priceNode != null && !priceNode.isNull() ? priceNode.asDouble() : 0.0;

        JsonNode stockNode = node.get("stock");
        int stock = stockNode != null && !stockNode.isNull() ? stockNode.asInt() : 0;

        JsonNode descriptionNode = node.get("description");
        String description = descriptionNode != null && !descriptionNode.isNull() ? descriptionNode.asText() : null;

        if (id == null) {
            throw new JsonProcessingException("Product ID is missing or null") {};
        }

        // Skapa och returnera ProductDto-objektet med deserialiserade värden
        return new ProductDto(id, name, description, price, stock);
    }
}
