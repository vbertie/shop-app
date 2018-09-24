package server.electronics.order.domain;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
interface OrderRepository extends Repository<Order, Long> {

    Order save(Order order);

    List<Order> findAll();

    void deleteById(Long id);

    Optional<Order> findOrderById(Long id);
}
