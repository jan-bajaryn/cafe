package by.epam.cafe.controller;

import by.epam.cafe.dao.ProductDao;
import by.epam.cafe.dao.ProductGroupDao;
import by.epam.cafe.dao.UserRepo;
import by.epam.cafe.entity.Product;
import by.epam.cafe.entity.ProductGroup;
import by.epam.cafe.entity.User;
import by.epam.cafe.entity.enums.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@Slf4j
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductGroupDao productGroupDao;

    @GetMapping("/create_user")
    public String createUser(Model model) {
        model.addAttribute("roles", Role.values());
        return "/admin/create_user";
    }

    @PostMapping("/create_user")
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
                .isBlocked(false)
                .build();

        userRepo.save(build);

        return "redirect:/admin/user_list";
    }

    @GetMapping("/user_list")
    public String adminUserList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "/admin/user_list";
    }

    @GetMapping("/edit_user/{id}")
    public String editUser(Model model,
                           @PathVariable(name = "id") Long id) {

        List<Role> roles = new ArrayList<>(Arrays.asList(Role.values()));
        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            roles.remove(user.getRole());
            model.addAttribute("roles", roles);
            model.addAttribute("user", user);
        } else {
            model.addAttribute("roles", roles);
            model.addAttribute("user", null);
        }

        return "/admin/edit_user";
    }

    @PostMapping("/edit_user")
    public String editUser(
            @RequestParam(name = "id") Long id,
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
            @RequestParam(name = "email") String email,
            @RequestParam(name = "isBlocked", required = false) String isBlocked
    ) {


        log.info("id = {}", id);
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


        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();

            user.setUsername(username);
            user.setPassword(password);
            user.setRole(Role.valueOf(role));
            user.setName(name);
            user.setSurname(surname);
            user.setStreet(street);
            user.setHouse(house);
            user.setRoom(room);
            user.setPorch(porch);
            user.setFloor(floor);
            user.setPhone(phone);
            user.setEmail(email);
            user.setIsBlocked(isBlocked != null);

            userRepo.save(user);
        }

        return "redirect:/admin/user_list";
    }


    @PostMapping("/block/{id}")
    public String adminBlockUId(@PathVariable(name = "id") Long id) {
        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            if (!user.getIsBlocked()) {
                user.setIsBlocked(true);
                userRepo.save(user);
            }
        }
        return "redirect:/admin/user_list";
    }

    @PostMapping("/unblock/{id}")
    public String adminUnBlockUId(@PathVariable(name = "id") Long id) {
        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            if (user.getIsBlocked()) {
                user.setIsBlocked(false);
                userRepo.save(user);
            }
        }
        return "redirect:/admin/user_list";
    }


    @GetMapping("/product_group_list")
    public String productGroupList(Model model) {
        model.addAttribute("groups", productGroupDao.findAll());
        return "/admin/product_group_list";
    }

    @GetMapping("/product_list")
    public String productList() {
        return "/admin/product_list";
    }

    @GetMapping("/create_product_group")
    public String createProductGroup() {
        return "/admin/create_product_group";
    }

    @GetMapping("/create_product")
    public String createProduct() {
        return "/admin/create_product";
    }


    @PostMapping("/disable_product_group")
    public String disableProductGroup(@RequestParam(name = "id") Long id) {
        Optional<ProductGroup> byId = productGroupDao.findById(id);
        if (byId.isPresent()) {
            ProductGroup productGroup = byId.get();
            if (!productGroup.isDisabled()) {
                productGroup.setDisabled(true);
                productGroupDao.save(productGroup);
            }
        }
        return "redirect:/admin/product_group_list";
    }

    @PostMapping("/enable_product_group")
    public String enableProductGroup(@RequestParam(name = "id") Long id) {
        Optional<ProductGroup> byId = productGroupDao.findById(id);
        if (byId.isPresent()) {
            ProductGroup productGroup = byId.get();
            if (productGroup.isDisabled()) {
                productGroup.setDisabled(false);
                productGroupDao.save(productGroup);
            }
        }
        return "redirect:/admin/product_group_list";
    }

}
