package tojohansson.customer.rmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tojohansson.customer.dto.CustomerDto;
import tojohansson.customer.services.CustomerService;

@Component
public class RabbitMQReceiver {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private RabbitMQSender rabbitSender;

    @RabbitListener(queues = RabbitMQConfig.CUSTOMER_INFO_REQUEST_QUEUE)
    public void receiveCustomerId(Message message){
        try {
            String messageBody = new String(message.getBody());
            Long id = Long.valueOf(messageBody.trim());

            findAndSendCustomerData(id);
        } catch (NumberFormatException e) {
            System.err.println("Failed to parse message body to Long: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    private void findAndSendCustomerData(Long id){
        if(customerService.customerExists(id)){
           CustomerDto dto = customerService.getCustomerById(id);
            rabbitSender.sendCustomerData(dto);
        }
    }

}
