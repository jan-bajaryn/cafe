package by.epam.cafe.controller;

import by.epam.cafe.dao.UserRepo;
import by.epam.cafe.entity.User;
import by.epam.cafe.entity.enums.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class AdminController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/admin/create_user")
    public String createUser(Model model) {
        model.addAttribute("roles", Role.values());
        return "/admin/create_user";
    }

    @PostMapping("/admin/create_user")
    public String adminCreateUser(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "role") String role,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "street") String street,
            @RequestParam(name = "house") String house,
            @RequestParam(name = "room") String room,
            @RequestParam(name = "porch") String porch,
            @RequestParam(name = "floor") String floor,
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "email") String email
    ) {

//        username,password,role,name,surname,street,house,room,porch,floor,phone,email

        log.info("username = {}", username);
        log.info("password = {}", password);
        log.info("role = {}", role);
        log.info("name = {}", name);
        log.info("surname = {}", surname);
        log.info("street = {}", street);
        log.info("house = {}", house);
        log.info("room = {}", room);
        log.info("porch = {}", porch);
        log.info("floor = {}", floor);
        log.info("phone = {}", phone);
        log.info("email = {}", email);


        User build = User.builder()
                .username(username)
                .password(password)
                .role(Role.valueOf(role))
                .name(name)
                .surname(surname)
                .street(street)
                .house(house)
                .room(room)
                .porch(porch)
                .floor(floor)
                .phone(phone)
                .email(email)
                .build();

        userRepo.save(build);

        return "redirect:/admin/create_user";
    }

    @GetMapping("/admin/user_list")
    public String adminUserList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "/admin/user_list";
    }

}
