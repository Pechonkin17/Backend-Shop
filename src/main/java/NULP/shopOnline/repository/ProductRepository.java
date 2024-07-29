package NULP.shopOnline.repository;

import NULP.shopOnline.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteProductById( long id);
    Optional<Product> findProductById(long id);
}
