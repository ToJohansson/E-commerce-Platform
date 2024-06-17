package tojohansson.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tojohansson.customer.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
