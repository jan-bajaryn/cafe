package by.epam.cafe.controller;

import by.epam.cafe.dao.UserRepo;
import by.epam.cafe.entity.User;
import by.epam.cafe.entity.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "/registration";
    }

    @PostMapping("/registration")
    public String createUser(@RequestParam(name = "email") String email,
                             @RequestParam(name = "phone") String phone,
                             @RequestParam(name = "username") String username,
                             @RequestParam(name = "password") String password,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "surname") String surname,
                             @RequestParam(name = "street") String street,
                             @RequestParam(name = "house") String house,
                             @RequestParam(name = "room") String room,
                             @RequestParam(name = "porch") String porch,
                             @RequestParam(name = "floor") String floor
    ) {

        User build = User.builder()
                .email(email)
                .phone(phone)
                .username(username)
                .password(password)
                .name(name)
                .surname(surname)
                .street(street)
                .house(house)
                .room(room)
                .porch(porch)
                .floor(floor)
                .role(Role.CLIENT)
                .build();

        userRepo.save(build);

        return "redirect:/";
//        return "redirect:/login";
    }

}
