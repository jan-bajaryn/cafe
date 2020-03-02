package by.epam.cafe.controller;

import by.epam.cafe.dao.ProductDao;
import by.epam.cafe.entity.Product;
import by.epam.cafe.entity.enums.ProductType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private ProductDao productDao;

    @GetMapping("/")
    public String index(@RequestParam(name = "type", required = false) String productType,
                        Model model) {
        if (productType == null || productType.isEmpty()) {
            List<Product> allByType = productDao.findAllByType(ProductType.PIZZA);
            model.addAttribute("products", allByType);
            return "/index";
        }
        try {
            ProductType type = ProductType.valueOf(productType);
            List<Product> allByType = productDao.findAllByType(type);
            model.addAttribute("products", allByType);
        } catch (IllegalArgumentException e) {
            log.info("Illegal input in index, productType = {}", productType);
            return "/errors/no-such-products";
        }
        return "/index";
    }

    @GetMapping("/order")
    public String order() {
        return "/order";
    }

    @GetMapping("client_cabinet")
    public String clientCabinet() {
        return "/client_cabinet";
    }
}
