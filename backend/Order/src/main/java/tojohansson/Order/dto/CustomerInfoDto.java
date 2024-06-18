package tojohansson.Order.dto;

public class CustomerInfoDto {

    private Long CustomerId;
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerMail;

    public CustomerInfoDto() {
    }

    public Long getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Long customerId) {
        CustomerId = customerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCustomerMail() {
        return CustomerMail;
    }

    public void setCustomerMail(String customerMail) {
        CustomerMail = customerMail;
    }
}
