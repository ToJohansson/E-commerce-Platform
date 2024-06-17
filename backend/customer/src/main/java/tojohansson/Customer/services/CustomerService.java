package tojohansson.customer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tojohansson.customer.dto.CustomerDto;
import tojohansson.customer.models.Customer;
import tojohansson.customer.repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService() {
    }

    // post
    public Customer createCustomer(CustomerDto cDto){
        Customer customer = (mapToCustomer(cDto, new Customer()));
        return customerRepository.save(customer);
    }

    // get

    // put

    // delete

    private Customer mapToCustomer(CustomerDto cDto, Customer customer){
        customer.setName(cDto.getName());
        customer.setAddress(cDto.getAddress());
        customer.setMail(cDto.getMail());
        return customer;
    }
    private CustomerDto mapCustomerToDto(Customer customer){
        CustomerDto dto = new CustomerDto();
        dto.setName(customer.getName());
        dto.setAddress(customer.getAddress());
        dto.setMail(customer.getMail());
        return dto;
    }

}
