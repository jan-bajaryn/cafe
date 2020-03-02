package by.epam.cafe.entity;

import by.epam.cafe.entity.join.IngredientProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ingredient {
    @Id
    private Long id;
    private String name;

    @OneToMany
    private List<IngredientProduct> ingredientProducts;
}
