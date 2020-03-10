package by.epam.cafe.controller;

import by.epam.cafe.controller.session.Basket;
import by.epam.cafe.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Controller
//@RestController
public class OrderRestController {

    @Autowired
    private Basket basket;


//    @GetMapping("/minus")
//    public Integer minus(@RequestParam(name = "product_id") Long productId) {
//        basket.getProducts().stream()
//                .filter(p -> p.getId().equals(productId))
//                .findAny().ifPresent(product -> basket.getProducts().remove(product));
//
//        return retCount(productId);
//    }
//
//    @GetMapping("/plus")
//    public Integer plus(@RequestParam(name = "product_id") Long productId) {
//        basket.getProducts().stream()
//                .filter(p -> p.getId().equals(productId))
//                .findAny().ifPresent(product -> basket.getProducts().add(product));
//
//        return retCount(productId);
//    }

    private int retCount(@RequestParam(name = "product_id") Long productId) {
        return (int) basket.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .count();
    }

}
