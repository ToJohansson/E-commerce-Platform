package tojohansson.Product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tojohansson.Product.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
