package NULP.shopOnline.service;

import NULP.shopOnline.exception.BucketNotFoundException;
import NULP.shopOnline.model.Bucket;
import NULP.shopOnline.model.Product;
import NULP.shopOnline.model.User;
import NULP.shopOnline.repository.BucketRepository;
import NULP.shopOnline.repository.ProductRepository;
import NULP.shopOnline.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BucketService {
    private final BucketRepository bucketRepository;

    public BucketService(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }


    public Bucket createBucket(User user, Product product) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        bucket.setProduct(product);
        return bucketRepository.save(bucket);
    }

    public List<Bucket> getBucketsByUser(User user) {
        return bucketRepository.findByUser(user);
    }

    public void deleteBucket(Bucket bucket) {
        bucketRepository.delete(bucket);
    }

}

