package tojohansson.Order.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import tojohansson.Order.dto.CustomerDto;

import java.io.IOException;

@Component
public class CustomerDtoDeserializer extends JsonDeserializer<CustomerDto> {

    @Override
    public CustomerDto deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Long id = node.get("id").asLong();
        String name = node.get("name").asText();
        String address = node.get("address").asText();
        String mail = node.get("mail").asText();

        CustomerDto dto = new CustomerDto();
        dto.setId(id);
        dto.setName(name);
        dto.setAddress(address);
        dto.setMail(mail);

        return dto;
    }
}
