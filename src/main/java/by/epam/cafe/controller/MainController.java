package by.epam.cafe.controller;

import by.epam.cafe.controller.session.Basket;
import by.epam.cafe.dao.ProductDao;
import by.epam.cafe.dao.ProductGroupDao;
import by.epam.cafe.entity.Product;
import by.epam.cafe.entity.ProductGroup;
import by.epam.cafe.entity.enums.ProductType;
//import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private ProductGroupDao productGroupDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private Basket basket;

    @GetMapping("/")
    public String index(@RequestParam(name = "type", required = false) String productType,
                        Model model) {
        log.info("basket.products = {}", basket.getProducts());
        if (productType == null || productType.isEmpty()) {
            List<ProductGroup> allByType = productGroupDao.findAllByType(ProductType.PIZZA);
//            Map<ProductGroup, Integer> withPrice = allByType.stream()
//                    .collect(Collectors.toMap(p -> p, p -> productDao.findMinPriceByProductGroup(p)));
            Map<Map.Entry<ProductGroup, Integer>, List<Product>> withPrice = allByType.stream()
                    .collect(Collectors.toMap(p -> Map.entry(p, productDao.findMinPriceByProductGroup(p)),
                            p -> productDao.findAllByProductGroup(p)));
            log.info("withPrice = {}", withPrice);

            model.addAttribute("products", withPrice);
            model.addAttribute("basket", basket.getProducts().size());

            return "/index";
        }
        try {
            ProductType type = ProductType.valueOf(productType);
            List<ProductGroup> allByType = productGroupDao.findAllByType(type);
            Map<Map.Entry<ProductGroup, Integer>, List<Product>> withPrice = allByType.stream()
                    .collect(Collectors.toMap(p -> Map.entry(p, productDao.findMinPriceByProductGroup(p)),
                            p -> productDao.findAllByProductGroup(p)));
            log.info("withPrice = {}", withPrice);

            model.addAttribute("products", withPrice);
            model.addAttribute("basket", basket.getProducts().size());

        } catch (IllegalArgumentException e) {
            log.info("Illegal input in index, productType = {}", productType);
            return "/errors/no-such-products";
        }
        return "/index";
    }

    @GetMapping("/put_item")
    public String putItem(@RequestParam(name = "variant", required = false) Long variant,
                          Model model) {

        if (variant == null) {
            return "redirect:/";
        }

        Optional<Product> byId = productDao.findById(variant);
        if (byId.isPresent()) {
            Product e = byId.get();
            basket.getProducts().add(e);
            log.info("e = {}", e);
            log.info("basket.getProducts() = {}", basket.getProducts());
            log.info("size basket = {}", basket.getProducts().size());
        } else {
            return "redirect:/errors/no-such-products";
        }
        return "redirect:/";
    }

    @GetMapping("/order")
    public String order(Model model) {

        Map<Product, Long> productMap = basket.getProducts().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Integer sum = productMap.entrySet().stream()
                .map(p -> p.getKey().getPrice() * p.getValue())
                .reduce(0L, Long::sum).intValue();
        log.info("sum = {}", sum);
        log.info("productMap = {}", productMap);

        model.addAttribute("productMap", productMap);
        model.addAttribute("sum", sum);

        return "/order";
    }

    @GetMapping("client_cabinet")
    public String clientCabinet() {
        return "/client_cabinet";
    }
}
