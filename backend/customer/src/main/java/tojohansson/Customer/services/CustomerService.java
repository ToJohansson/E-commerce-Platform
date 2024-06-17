package tojohansson.customer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tojohansson.customer.dto.CustomerDto;
import tojohansson.customer.exceptions.EntityNotFoundException;
import tojohansson.customer.models.Customer;
import tojohansson.customer.repositories.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

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
    public CustomerDto getCustomerById(Long id) {
        Customer customer = checkCustomerById(id);
        return (mapCustomerToDto(customer));
    }

    public List<CustomerDto> allCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::mapCustomerToDto)
                .collect(Collectors.toList());
    }

    // put
    public Customer updateCustomer(CustomerDto customerDto) {
        Customer customer = checkCustomerById(customerDto.getId());
        customer = (mapToCustomer(customerDto, customer));
        return customerRepository.save(customer);
    }

    // delete
    public void deleteCustomer(Long id) {
        Customer customer = checkCustomerById(id);
        customerRepository.deleteById(customer.getId());

    }

    private Customer checkCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Customer with ID [" + id + "], not found. "));
    }

    private Customer mapToCustomer(CustomerDto cDto, Customer customer) {
        customer.setName(cDto.getName());
        customer.setAddress(cDto.getAddress());
        customer.setMail(cDto.getMail());
        return customer;
    }

    private CustomerDto mapCustomerToDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setAddress(customer.getAddress());
        dto.setMail(customer.getMail());
        return dto;
    }

}
