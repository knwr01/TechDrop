package ca.humber.techdrop.service;

import ca.humber.techdrop.model.Product;
import ca.humber.techdrop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByProductNameContainingIgnoreCase(keyword);
    }

    public List<Product> sortProducts(String sortBy) {

        if ("priceAsc".equals(sortBy)) {
            return productRepository.findAll(Sort.by("price").ascending());
        }

        if ("priceDesc".equals(sortBy)) {
            return productRepository.findAll(Sort.by("price").descending());
        }

        if ("name".equals(sortBy)) {
            return productRepository.findAll(Sort.by("productName").ascending());
        }

        return productRepository.findAll();
    }
}