package by.epam.cafe.dao;

import by.epam.cafe.entity.Product;
import by.epam.cafe.entity.ProductGroup;
import by.epam.cafe.entity.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductGroupDao extends JpaRepository<ProductGroup, Long> {

    //    List<Product> findAllByType(ProductType pizza);
    List<ProductGroup> findAllByType(ProductType type);

    ProductGroup findByProductsContains(Product product);

}
