package ca.humber.techdrop.repository;

import ca.humber.techdrop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}