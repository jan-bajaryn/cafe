package by.epam.cafe.dao;

import by.epam.cafe.entity.Product;
import by.epam.cafe.entity.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {
    @Query("SELECT min(p.price) FROM Product p where p.productGroup = ?1")
    Integer findMinPriceByProductGroup(ProductGroup productGroup);

    List<Product> findAllByProductGroup(ProductGroup p);
}
