package by.epam.cafe.entity;

import by.epam.cafe.entity.enums.ProductType;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
//@Getter
//@Setter
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(unique = true)
    @NotEmpty
    private String name;

    private String description;

    @Column(unique = true)
    @NotEmpty
    private String photoName;

    @Enumerated(value = EnumType.ORDINAL)
    @NotNull
    private ProductType type;

    @OneToMany(fetch = FetchType.EAGER/*, mappedBy = "productGroup"*/)
    private Set<Product> products;

    private boolean disabled;
}
