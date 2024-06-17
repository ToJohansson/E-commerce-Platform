package tojohansson.Order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tojohansson.Order.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
