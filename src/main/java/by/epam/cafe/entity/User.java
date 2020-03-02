package by.epam.cafe.entity;

import by.epam.cafe.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

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

    @CreatedDate
    private LocalDate creation;
    private String address;
    private String phone;


}
