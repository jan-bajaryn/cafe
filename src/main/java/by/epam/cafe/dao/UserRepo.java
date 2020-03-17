package by.epam.cafe.dao;

import by.epam.cafe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepo extends JpaRepository<User, Long> {
//    User findByUsername(String username);
    User findByUsername(String username);
}
