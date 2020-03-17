package by.epam.cafe.config.hepl;

import by.epam.cafe.dao.UserRepo;
import by.epam.cafe.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public UserDetailsImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username = {}", username);
        User byUsername = userRepo.findByUsername(username);
        log.info("byUsername = {}", byUsername);
        if (byUsername == null) {
            throw new UsernameNotFoundException("from loadUserByUsername");
        }
        return byUsername;
    }
}
