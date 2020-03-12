package by.epam.cafe.entity;

import by.epam.cafe.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;

    @Enumerated(value = EnumType.ORDINAL)
    private Role role;
    private String name;
    private String surname;

    //    @CreatedDate
//    @CreatedDate

    @CreationTimestamp
    private LocalDateTime creation;

    private String address;
    private String phone;


}
