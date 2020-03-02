package by.epam.cafe.controller;

import by.epam.cafe.dao.ProductDao;
import by.epam.cafe.dao.ProductGroupDao;
import by.epam.cafe.entity.ProductGroup;
import by.epam.cafe.entity.enums.ProductType;
//import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private ProductGroupDao productGroupDao;

    @Autowired
    private ProductDao productDao;

    @GetMapping("/")
    public String index(@RequestParam(name = "type", required = false) String productType,
                        Model model) {
        if (productType == null || productType.isEmpty()) {
            List<ProductGroup> allByType = productGroupDao.findAllByType(ProductType.PIZZA);
            Map<ProductGroup, Integer> withPrice = allByType.stream()
                    .collect(Collectors.toMap(p -> p, p -> productDao.findMinPriceByProductGroup(p)));
            log.info("withPrice = {}", withPrice);
            model.addAttribute("products", withPrice);
            return "/index";
        }
        try {
            ProductType type = ProductType.valueOf(productType);
            List<ProductGroup> allByType = productGroupDao.findAllByType(type);
            Map<ProductGroup, Integer> withPrice = allByType.stream()
                    .collect(Collectors.toMap(p -> p, p -> productDao.findMinPriceByProductGroup(p)));
            log.info("withPrice = {}", withPrice);
            model.addAttribute("products", withPrice);
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
