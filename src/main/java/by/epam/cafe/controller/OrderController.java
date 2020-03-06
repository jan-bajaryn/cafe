package by.epam.cafe.controller;

import by.epam.cafe.controller.session.Basket;
import by.epam.cafe.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class OrderController {

    @Autowired
    private Basket basket;

    @GetMapping("/deleteAll")
    public String delete(@RequestParam(name = "id") Long id) {
        Product product = basket.getProducts().stream()
                .filter(p -> p.getId().equals(id))
                .findAny()
                .orElse(null);
        if (product != null) {
            basket.getProducts().removeAll(Collections.singleton(product));
        }
        return "redirect:/order";
    }
}
