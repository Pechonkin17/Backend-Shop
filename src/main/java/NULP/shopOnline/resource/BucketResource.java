package NULP.shopOnline.resource;

import NULP.shopOnline.exception.BucketNotFoundException;
import NULP.shopOnline.model.Bucket;
import NULP.shopOnline.model.Product;
import NULP.shopOnline.model.User;
import NULP.shopOnline.repository.BucketRepository;
import NULP.shopOnline.repository.ProductRepository;
import NULP.shopOnline.repository.UserRepository;
import NULP.shopOnline.service.BucketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/buckets")
public class BucketResource {
    private final BucketService bucketService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BucketRepository bucketRepository;


    public BucketResource(BucketService bucketService, ProductRepository productRepository,
                          UserRepository userRepository, BucketRepository bucketRepository) {
        this.bucketService = bucketService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.bucketRepository = bucketRepository;
    }

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<Bucket> createBucket(@PathVariable("userId") long userId, @PathVariable("productId") long productId) {
        // Assuming you have a User and Product object available
        User user = getUserById(userId);
        Product product = getProductById(productId);

        Bucket bucket = bucketService.createBucket(user, product);
        return ResponseEntity.ok(bucket);
    }



    @GetMapping("/{userId}")
    public ResponseEntity<List<Bucket>> getBucketsByUser(@PathVariable("userId") long userId) {
        // Assuming you have a User object available
        User user = getUserById(userId);

        List<Bucket> buckets = bucketService.getBucketsByUser(user);
        return ResponseEntity.ok(buckets);
    }

    @DeleteMapping("/{bucketId}")
    public ResponseEntity<Void> deleteBucket(@PathVariable("bucketId") long bucketId) {
        Bucket bucket = getBucketById(bucketId);
        bucketService.deleteBucket(bucket);
        return ResponseEntity.noContent().build();
    }

    // Utility methods to get User, Product, and Bucket by their IDs

    private User getUserById(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new BucketNotFoundException("User not found with id: " + userId);
        }
    }

    private Product getProductById(long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new BucketNotFoundException("Product not found with id: " + productId);
        }
    }

    private Bucket getBucketById(long bucketId) {
        Optional<Bucket> optionalBucket = bucketRepository.findById(bucketId);
        if (optionalBucket.isPresent()) {
            return optionalBucket.get();
        } else {
            throw new BucketNotFoundException("Bucket not found with id: " + bucketId);
        }
    }
}

