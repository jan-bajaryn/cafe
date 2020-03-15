package by.epam.cafe.controller.interceptor;

import by.epam.cafe.controller.session.Basket;
import by.epam.cafe.dao.ProductDao;
import by.epam.cafe.dao.ProductGroupDao;
import by.epam.cafe.entity.Product;
import by.epam.cafe.entity.ProductGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
//@Component
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BasketInterceptor implements HandlerInterceptor {

    @Autowired
    private Basket basket;

    @Autowired
    private ProductGroupDao productGroupDao;

    @Autowired
    private ProductDao productDao;


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        List<Product> products = basket.getProducts();
        log.info("products = {}", products);

        List<Long> before = productGroupDao.findAll().stream()
                .filter(ProductGroup::isDisabled)
                .map(ProductGroup::getId)
                .distinct()
                .collect(Collectors.toList());

        log.info("before = {}", before);

        List<Product> after = products.stream()
                .filter(p -> before.contains(p.getProductGroup().getId()))
                .collect(Collectors.toList());

        log.info("after = {}", after);

        for (Product product : after) {
            products.remove(product);
        }
        log.info("products after = {}", products);
        log.info("intercepter executed");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
