package by.epam.cafe.dao;

import by.epam.cafe.entity.DeliveryInf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryInfRepo extends CrudRepository<DeliveryInf, Long> {
    //    DeliveryInf findByOrder(Order order);
}
