package tojohansson.Order.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tojohansson.Order.dto.OrderDto;
import tojohansson.Order.models.Order;
import tojohansson.Order.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {"http://localhost:5173", "https://e-commerce-platform-sepia.vercel.app"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    public OrderController() {
    }

    // GET
    @GetMapping("/")
    public ResponseEntity<List<OrderDto>> getallOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> orderById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderService.orderById(id), HttpStatus.OK);
    }

    // POST
    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto dto) throws JsonProcessingException {
        return new ResponseEntity<>(orderService.createOrder(dto), HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, @RequestBody OrderDto dto) {
        dto.setId(id);
        return new ResponseEntity<>(orderService.updateOrder(id, dto), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id) {
        try {
            orderService.deleteOrder(id);
            String message = "Order was deleted.";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            String message = "Opsss, something went wrong... " + e.getMessage();
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
