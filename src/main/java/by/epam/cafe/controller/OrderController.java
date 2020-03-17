package by.epam.cafe.controller;

import by.epam.cafe.controller.session.Basket;
import by.epam.cafe.dao.DeliveryInfRepo;
import by.epam.cafe.dao.OrderRepo;
import by.epam.cafe.entity.DeliveryInf;
import by.epam.cafe.entity.Order;
import by.epam.cafe.entity.Product;
import by.epam.cafe.entity.User;
import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.PaymentType;
import by.epam.cafe.entity.enums.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@Slf4j
@PreAuthorize("hasAuthority('CLIENT') || !isAuthenticated()")
public class OrderController {

    @Autowired
    private Basket basket;

    @Autowired
    private DeliveryInfRepo deliveryInfRepo;

    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/deleteAll")
    public String delete(@RequestParam(name = "id") Long id) {
        basket.getProducts().stream()
                .filter(p -> p.getId().equals(id))
                .findAny().ifPresent(product -> basket.getProducts().removeAll(Collections.singleton(product)));
        return "redirect:/order";
    }


    @GetMapping("/minus")
    public String minus(@RequestParam(name = "product_id") Long productId) {
        basket.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findAny().ifPresent(product -> basket.getProducts().remove(product));

        return "redirect:/order";
    }

    @GetMapping("/plus")
    public String plus(@RequestParam(name = "product_id") Long productId) {
        basket.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findAny().ifPresent(product -> basket.getProducts().add(product));

        return "redirect:/order";
    }

    @PostMapping("/make_order")
    public String makeOrder(@RequestParam(name = "street") String street,
                            @RequestParam(name = "house") String house,
                            @RequestParam(name = "room") String room,
                            @RequestParam(name = "porch") String porch,
                            @RequestParam(name = "floor") String floor,
                            @RequestParam(name = "comments") String comments,//
                            @RequestParam(name = "email") String email,
                            @RequestParam(name = "tel") String tel,
                            @RequestParam(name = "name") String clientName,
                            @RequestParam(name = "time") String time,
                            @AuthenticationPrincipal User user
    ) {
        log.info("makeOrder: street = {}", street);
        log.info("makeOrder: house = {}", house);
        log.info("makeOrder: room = {}", room);
        log.info("makeOrder: porch = {}", porch);
        log.info("makeOrder: floor = {}", floor);
        log.info("makeOrder: comments = {}", comments);
        log.info("email = {}", email);
        log.info("tel = {}", tel);
        log.info("clientName = {}", clientName);
        log.info("time = {}", time);
        log.info("user = {}", user);

//        if (user != null) {
//            if (user.getRole() != Role.CLIENT) {
//                return "redirect:/errors/no-such-products";
//            }
//        }


        saveOrder(street, house, room, porch, floor, email, tel, clientName, user);
        return "redirect:/";
    }


    public void saveOrder(String street, String house, String room, String porch, String floor, String email, String tel, String clientName, User user) {

        DeliveryInf deliveryInf = DeliveryInf.builder()
                .street(street)
                .house(house)
                .room(room)
                .porch(porch)
                .floor(floor)
                .email(email)
                .phone(tel)
                .clientName(clientName)
//                .deliveryTime(LocalDateTime.parse(time))
                .build();

        DeliveryInf save = deliveryInfRepo.save(deliveryInf);

        Order order = Order.builder()
                .products(basket.getProducts())
                .deliveryInf(save)
                .paymentType(PaymentType.CASH)
                .price(calcSum(basket))
                .status(OrderStatus.WAITING)
                .user(user)
                .build();


        orderRepo.save(order);
        basket.setProducts(new ArrayList<>());
    }

    private Integer calcSum(Basket basket) {
        return basket.getProducts().stream()
                .map(Product::getPrice)
                .reduce(Integer::sum).orElse(0);
    }


    @GetMapping("/your-order/{id}")
    public String yourOrder(Model model, @PathVariable(name = "id") Long id) {
        Order order = orderRepo.findById(id).orElse(null);
        if (order == null) {
            return "errors/no-such-products";
        }

        model.addAttribute("order", order);
        model.addAttribute("basket", basket.getProducts().size());

        Map<Product, Long> productMap = order.getProducts().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        log.info("productMap = {}", productMap);
        log.info("order.getProducts() = {}", order.getProducts());

        Integer sum = productMap.entrySet().stream()
                .map(p -> p.getKey().getPrice() * p.getValue())
                .reduce(0L, Long::sum).intValue();
        log.info("sum = {}", sum);
        model.addAttribute("productMap", productMap);
        model.addAttribute("sum", sum);
        return "/your-order";
    }


//    @GetMapping("/your-order")
//    public String yourOrder(Model model) {
//        Order order = orderRepo.findById(2l).orElse(null);
//        if (order == null) {
//            return "errors/no-such-products";
//        }
//
//        model.addAttribute("order", order);
//        model.addAttribute("basket", basket.getProducts().size());
//
//        return "/your-order";
//    }

}
