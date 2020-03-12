package by.epam.cafe.dao;

import by.epam.cafe.entity.DeliveryInf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryInfRepo extends JpaRepository<DeliveryInf, Long> {
    //    DeliveryInf findByOrder(Order order);
}
