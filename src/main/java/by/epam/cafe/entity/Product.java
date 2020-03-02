package by.epam.cafe.entity;

import by.epam.cafe.entity.enums.ProductSize;
import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.join.IngredientProduct;
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

    private String name;
    private String description;
    private String photoName;
    private Integer price;
    @Enumerated(value = EnumType.ORDINAL)
    private ProductType type;

    @Enumerated(value = EnumType.ORDINAL)
    private ProductSize size;

    @OneToMany
    private List<IngredientProduct> ingredientProducts;
}
