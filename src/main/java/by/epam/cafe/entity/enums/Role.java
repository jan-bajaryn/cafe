package by.epam.cafe.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, OPERATOR, CLIENT;

    @Override
    public String getAuthority() {
        return name();
    }

}
