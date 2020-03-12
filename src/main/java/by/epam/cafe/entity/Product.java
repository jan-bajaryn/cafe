package by.epam.cafe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = {"product_group_id", "weight"})})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer price;

    @Column(name = "weight")
    private Integer weight;

    @ManyToOne
    @JoinColumn(name = "product_group_id")
    private ProductGroup productGroup;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

}
