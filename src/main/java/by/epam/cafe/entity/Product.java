package by.epam.cafe.entity;

import by.epam.cafe.entity.enums.ProductSize;
import by.epam.cafe.entity.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer price;
    private Integer weight;

    @ManyToOne
    private ProductGroup productGroup;

}
