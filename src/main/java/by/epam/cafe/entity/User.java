package by.epam.cafe.entity;

import by.epam.cafe.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @Enumerated(value = EnumType.ORDINAL)
    private Role role;

    @NotNull
    @NotEmpty
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
    @NotEmpty
    private String email;


    @NotNull
    private Boolean isBlocked;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isBlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

