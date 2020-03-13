package by.epam.cafe.entity;

import by.epam.cafe.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @Enumerated(value = EnumType.ORDINAL)
    private Role role;

    @NotNull
    private String name;
    private String surname;

    @CreationTimestamp
    private LocalDateTime creation;

    private String street;
    private String house;
    private String room;
    private String porch;
    private String floor;

    private String phone;

    @NotNull
    private String email;

}
