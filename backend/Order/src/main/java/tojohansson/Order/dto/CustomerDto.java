package tojohansson.Order.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import tojohansson.Order.deserialize.CustomerDtoDeserializer;

@JsonDeserialize(using = CustomerDtoDeserializer.class)
public class CustomerDto {

    private Long id;
    private String name;
    private String address;
    private String mail;

    public CustomerDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
