package NULP.shopOnline.service;


import NULP.shopOnline.exception.ProductNotFoundException;
import NULP.shopOnline.model.Product;
import NULP.shopOnline.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Product updatedProduct) {
        // Знайдіть продукт за його ідентифікатором
        Optional<Product> existingProduct = productRepository.findById(updatedProduct.getId());
        if (existingProduct.isPresent()) {
            // Оновіть поля продукту з вхідного об'єкту
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setImage(updatedProduct.getImage());

            // Збережіть оновлений продукт у базі даних
            Product savedProduct = productRepository.save(product);

            return savedProduct;
        } else {
            // Обробка помилки, якщо продукт не знайдено
            throw new ProductNotFoundException("Product not found with id: " + updatedProduct.getId());
        }
    }

    public Product findProductById (long id) {
        return productRepository.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product by id" + id + "was not found"));
    }

    public void deleteProduct(long id) {
        productRepository.deleteProductById(id);
    }
}
