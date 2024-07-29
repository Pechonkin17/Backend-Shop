package NULP.shopOnline.repository;

import NULP.shopOnline.model.Bucket;
import NULP.shopOnline.model.Product;
import NULP.shopOnline.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
    List<Bucket> findByUser(User user);

    Bucket save(Bucket bucket);

    void delete(Bucket bucket);
    Optional<Bucket> findById(Long id);

}
