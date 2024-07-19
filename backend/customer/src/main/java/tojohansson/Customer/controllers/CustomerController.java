package tojohansson.customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tojohansson.customer.dto.CustomerDto;
import tojohansson.customer.models.Customer;
import tojohansson.customer.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = {"http://localhost:5173", "https://e-commerce-platform-sepia.vercel.app"})
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public CustomerController() {
    }

    // get
    @GetMapping("/")
    public List<CustomerDto> getAllCustomers() {
        return customerService.allCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> customerById(@PathVariable("id") Long id) {
        CustomerDto customerDto = customerService.getCustomerById(id);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    // post
    @PostMapping("/")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto dto) {
        return new ResponseEntity<>(customerService.createCustomer(dto), HttpStatus.CREATED);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto dto) {
        dto.setId(id);
        return new ResponseEntity<>(customerService.updateCustomer(dto),HttpStatus.OK);
    }
    // Delete
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id){
        try {
            String message = "DELETE was successful";
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(message,HttpStatus.OK);

        } catch (Exception e){
            String message = "Opsss, Something went wrong... " + e.getMessage();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

    }
}
