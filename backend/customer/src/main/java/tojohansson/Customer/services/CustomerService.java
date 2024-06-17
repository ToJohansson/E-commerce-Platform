package tojohansson.customer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tojohansson.customer.dto.CustomerDto;
import tojohansson.customer.exceptions.EntityNotFoundException;
import tojohansson.customer.models.Customer;
import tojohansson.customer.repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService() {
    }

    // post
    public Customer createCustomer(CustomerDto cDto) {
        Customer customer = (mapToCustomer(cDto, new Customer()));
        return customerRepository.save(customer);
    }

    // get

    // put
    public Customer updateCustomer(CustomerDto customerDto){
        Customer customer = checkCustomerById(customerDto.getId());
        customer = (mapToCustomer(customerDto, customer));
        return customerRepository.save(customer);
    }

    // delete
    public void deleteCustomer(Long id) {
        Customer customer = checkCustomerById(id);
        customerRepository.deleteById(customer.getId());

    }

    private Customer checkCustomerById(Long id){
        return customerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Customer with [" + id + "]; not found. "));
    }

    private Customer mapToCustomer(CustomerDto cDto, Customer customer) {
        customer.setName(cDto.getName());
        customer.setAddress(cDto.getAddress());
        customer.setMail(cDto.getMail());
        return customer;
    }

    private CustomerDto mapCustomerToDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setName(customer.getName());
        dto.setAddress(customer.getAddress());
        dto.setMail(customer.getMail());
        return dto;
    }

}
