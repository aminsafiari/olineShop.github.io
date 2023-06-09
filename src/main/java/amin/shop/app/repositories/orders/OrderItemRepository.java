package amin.shop.app.repositories.orders;

import amin.shop.app.entities.orders.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository
        extends CrudRepository<OrderItem, Long> {
}
