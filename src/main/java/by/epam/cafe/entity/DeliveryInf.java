package by.epam.cafe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryInf {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime deliveryTime;

    private String clientName;

    //    private String address;
    private String street;
    private String house;
    private String room;
    private String porch;
    private String floor;


    private String phone;
    private String email;

//    @OneToOne(fetch = FetchType.EAGER)
//    private Order order;
}
