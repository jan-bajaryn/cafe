package by.epam.cafe.entity;

import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    private LocalDate creation;

    private Integer price;

    @Enumerated(value = EnumType.ORDINAL)
    private OrderStatus status;

    @Enumerated(value = EnumType.ORDINAL)
    private PaymentType paymentType;

    @OneToOne
    @JoinColumn(name = "delivery_inf_id")
    private DeliveryInf deliveryInf;

    @ManyToMany/*(cascade = CascadeType.ALL)*/
//    @JoinTable(
//            name = "order_products",
//            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "products_id", referencedColumnName = "id")}
//    )
    private List<Product> products = new ArrayList<>();

}
