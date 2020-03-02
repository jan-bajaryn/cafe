package by.epam.cafe.dao;

import by.epam.cafe.entity.Product;
import by.epam.cafe.entity.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {
    List<Product> findAllByType(ProductType type);
}
