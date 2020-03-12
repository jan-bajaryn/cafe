package by.epam.cafe.controller;

import by.epam.cafe.dao.DeliveryInfRepo;
import by.epam.cafe.dao.OrderRepo;
import by.epam.cafe.entity.DeliveryInf;
import by.epam.cafe.entity.Order;
import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.PaymentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class OperatorController {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private DeliveryInfRepo deliveryInfRepo;

    @GetMapping("/order-list")
    public String orderList(Model model) {

        model.addAttribute("orders", orderRepo.findAll());

        return "/order-list";
    }

    @PostMapping("/cancel_order/{id}")
    public String cancelOrder(@PathVariable(name = "id") Long id) {
        orderRepo.findById(id).ifPresent(o -> {
            o.setStatus(OrderStatus.CANCELED);
            orderRepo.save(o);
        });
        return "redirect:/order-list";
    }

    @GetMapping("/edit-order/{id}")
    public String editOrder(Model model, @PathVariable(name = "id") Long id) {

        Optional<Order> byId = orderRepo.findById(id);
        if (byId.isPresent()) {
            Order or = byId.get();
            model.addAttribute("order", or);

            List<OrderStatus> statuses = Arrays.stream(OrderStatus.values())
                    .filter(s -> !s.equals(or.getStatus()))
                    .collect(Collectors.toList());
            model.addAttribute("statuses", statuses);

            List<PaymentType> types = Arrays.stream(PaymentType.values())
                    .filter(t -> t != or.getPaymentType())
                    .collect(Collectors.toList());

            model.addAttribute("types", types);
        } else {
            return "/errors/no-such-products";
        }

        return "/edit-order";
    }

    @PostMapping("/edit-order/confirm")
    public String editOrderConfirm(@RequestParam(name = "id") Long id,
                                   @RequestParam(name = "status") String orderStatus,
                                   @RequestParam(name = "payment_type") String paymentType,
                                   @RequestParam(name = "name") String clientName,
                                   @RequestParam(name = "time") String time,
                                   @RequestParam(name = "street") String street,
                                   @RequestParam(name = "house") String house,
                                   @RequestParam(name = "room") String room,
                                   @RequestParam(name = "porch") String porch,
                                   @RequestParam(name = "floor") String floor,
                                   @RequestParam(name = "tel") String tel,
                                   @RequestParam(name = "email") String email,
                                   @RequestParam(name = "comments") String comments,
                                   @RequestParam(name = "price") Integer price
    ) {

        Order order = orderRepo.findById(id).orElse(null);
        if (order == null || order.getDeliveryInf() == null) {
            return "redirecti:/errors/nono-such-products";
        }

        DeliveryInf save = findDeliveryInf(id, clientName, street, house, room, porch, floor, tel, email, comments, order.getDeliveryInf());
        saveOrder(id, orderStatus, paymentType, price, save, order);

        return "redirect:/order-list";
    }

    private void saveOrder(@RequestParam(name = "id") Long id, @RequestParam(name = "status") String orderStatus, @RequestParam(name = "payment_type") String paymentType, @RequestParam(name = "price") Integer price, DeliveryInf save, Order order) {

        order.setId(id);
        order.setStatus(OrderStatus.valueOf(orderStatus.toUpperCase()));
        order.setPaymentType(PaymentType.valueOf(paymentType));
        order.setPrice(price);
        order.setDeliveryInf(save);

        orderRepo.save(order);
    }

    private DeliveryInf findDeliveryInf(Long id,
                                        String clientName,
                                        String street,
                                        String house,
                                        String room,
                                        String porch,
                                        String floor,
                                        String tel, String email,
                                        String comments,
                                        DeliveryInf deliveryInf) {
        deliveryInf.setClientName(clientName);
//        deliveryInf.setDeliveryTime(LocalDateTime.parse(time));
        deliveryInf.setStreet(street);
        deliveryInf.setHouse(house);
        deliveryInf.setRoom(room);
        deliveryInf.setPorch(porch);
        deliveryInf.setFloor(floor);
        deliveryInf.setPhone(tel);
        deliveryInf.setEmail(email);
        deliveryInf.setComments(comments);
        return deliveryInfRepo.save(deliveryInf);
    }
}
