package pl.dolega.orderservice.product;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByDescription(String description);

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Product getReferenceById(Long aLong);
}
