package by.epam.cafe.controller;

import by.epam.cafe.dao.ProductDao;
import by.epam.cafe.dao.ProductGroupDao;
import by.epam.cafe.dao.UserRepo;
import by.epam.cafe.entity.Product;
import by.epam.cafe.entity.ProductGroup;
import by.epam.cafe.entity.User;
import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.enums.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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

    @Value("${download.path}")
    private String downloadPath;


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
    public String productList(Model model) {
//        List<Product> products = productDao.findAllByProductGroupNull();
//        log.info("productList: products = {}", products);

        model.addAttribute("products", productDao.findAll());

        return "/admin/product_list";
    }

    @GetMapping("/edit_product/{id}")
    public String editProduct(@PathVariable(name = "id") Long id,
                              Model model) {
        Product product = productDao.findById(id).orElse(null);
        model.addAttribute("product", product);
        List<ProductGroup> all = productGroupDao.findAll();
        if (product != null && product.getProductGroup() != null) {
            all.removeIf(p -> p.getId().equals(product.getProductGroup().getId()));
        }

        model.addAttribute("groups", all);
        return "/admin/edit_product";
    }

    @PostMapping("/edit_product")
    public String editProduct(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "product_group") Long productGroupId,
            @RequestParam(name = "price") Integer price,
            @RequestParam(name = "weight") Integer weight
    ) {


        log.info("id = {}", id);
        log.info("productGroupId = {}", productGroupId);
        log.info("price = {}", price);
        log.info("weight = {}", weight);

        Optional<Product> byId = productDao.findById(id);
        log.info("byId.isPresent() = {}", byId.isPresent());
        if (byId.isPresent()) {
            Product product = byId.get();
            product.setPrice(price);
            product.setWeight(weight);

            ProductGroup productGroupNew = productGroupDao.findById(productGroupId).orElse(null);
            log.info("productGroupNew = {}", productGroupNew);
            product.setProductGroup(productGroupNew);

            productDao.save(product);
        }

        return "redirect:/admin/product_list";
    }


    @GetMapping("/create_product")
    public String createProduct(Model model) {
        model.addAttribute("groups", productGroupDao.findAll());
        return "/admin/create_product";
    }

    @PostMapping("/create_product")
    public String createProduct(@RequestParam(name = "product_group") Long productGroupId,
                                @RequestParam(name = "price") Integer price,
                                @RequestParam(name = "weight") Integer weight) {
        Product product = new Product();
        product.setPrice(price);
        product.setWeight(weight);

        if (productGroupId != null) {
            productGroupDao.findById(productGroupId)
                    .ifPresent(product::setProductGroup);
        }
        productDao.save(product);
        return "redirect:/admin/product_list";
    }

    @GetMapping("/create_product_group")
    public String createProductGroup(Model model) {

        model.addAttribute("types", ProductType.values());
        model.addAttribute("products", productDao.findAllByProductGroupNull());

        return "/admin/create_product_group";
    }

    @PostMapping("/create_product_group")
    public String createProductGroup(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "file") MultipartFile multipartFile,
            @RequestParam(name = "type") ProductType type,
            @RequestParam(name = "products", required = false) List<Long> productIds
    ) {

        log.info("createProductGroup: name = {}", name);
        log.info("createProductGroup: description = {}", description);
        log.info("createProductGroup: multipartFile = {}", multipartFile);
        log.info("createProductGroup: type = {}", type);
        log.info("createProductGroup: productIds = {}", productIds);

        ProductGroup productGroup = new ProductGroup();

        productGroup.setName(name);

        productGroup.setDisabled(true);
        productGroup.setDescription(description);
        productGroup.setType(type);

        if (productIds != null) {
            Set<Product> products = productIds.stream()
                    //TODO change throw to something other
                    .map(id -> productDao.findById(id).orElseThrow(IllegalArgumentException::new))
                    .collect(Collectors.toSet());

            productGroup.setProducts(products);
        }


        if (multipartFile != null
                && multipartFile.getOriginalFilename() != null
                && !multipartFile.getOriginalFilename().isEmpty()) {
            File updoadDir = new File(downloadPath);
            if (!updoadDir.exists()) {
                updoadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + multipartFile.getOriginalFilename();

            try {
                multipartFile.transferTo(new File(downloadPath + File.separator + resultFileName));
                log.info("resultFileName = {}", resultFileName);
                productGroup.setPhotoName(resultFileName);
                productGroupDao.save(productGroup);
            } catch (IOException e) {
                return "redirect:/errors/no-such-products";
            }

        }

        return "redirect:/admin/product_group_list";
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


    @GetMapping("/edit_product_group/{id}")
    public String editProductGroup(
            @PathVariable(name = "id") Long id,
            Model model
    ) {
        Optional<ProductGroup> byId = productGroupDao.findById(id);
        if (byId.isPresent()) {
            ProductGroup productGroup = byId.get();
            EnumSet<ProductType> values = EnumSet.complementOf(EnumSet.of(productGroup.getType()));
            model.addAttribute("types", values);

            model.addAttribute("products", productDao.findAllByProductGroupNull());
            model.addAttribute("group", productGroup);

            log.info("productGroup.getProducts() = {}", productGroup.getProducts());


            return "/admin/edit_product_group";
        }

        return "/errors/no-such-products";
    }

    @PostMapping("/edit_product_group")
    public String editProductGroup(@RequestParam(name = "id") ProductGroup id,
                                   @RequestParam(name = "name") String name,
                                   @RequestParam(name = "description") String description,
                                   @RequestParam(name = "file") MultipartFile multipartFile,
                                   @RequestParam(name = "type") ProductType type,
                                   @RequestParam(name = "products", required = false) Set<Product> productIds
    ) {
        log.info("id = {}", id);
        log.info("name = {}", name);
        log.info("description = {}", description);
        log.info("multipartFile = {}", multipartFile);
        log.info("type = {}", type);
        log.info("productIds = {}", productIds);


        id.setName(name);

        id.setDisabled(true);
        id.setDescription(description);
        id.setType(type);

        for (Product product : productIds) {
            product.setProductGroup(id);
            productDao.save(product);
        }

        id.setProducts(productIds);


        if (multipartFile != null
                && multipartFile.getOriginalFilename() != null
                && !multipartFile.getOriginalFilename().isEmpty()) {
            File updoadDir = new File(downloadPath);
            if (!updoadDir.exists()) {
                updoadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + multipartFile.getOriginalFilename();

            try {
                multipartFile.transferTo(new File(downloadPath + File.separator + resultFileName));
                log.info("resultFileName = {}", resultFileName);
                id.setPhotoName(resultFileName);
            } catch (IOException e) {
                return "redirect:/errors/no-such-products";
            }

        }
        productGroupDao.save(id);


        return "redirect:/admin/product_group_list";
    }

}
