package by.epam.cafe.entity;

import by.epam.cafe.entity.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;
    private String description;
    private String photoName;

    @Enumerated(value = EnumType.ORDINAL)
    private ProductType type;

    @OneToMany(fetch = FetchType.EAGER)
//    @Column(unique = true)
    private Set<Product> products;

    private boolean disabled;
}
