package server.electronics.order.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class InMemoryOrderRepository  implements OrderRepository{

    private final Map<Long, Order> orders = new ConcurrentHashMap<>();

    @Override
    public Order save(Order order) {
        orders.put(order.getId(), order);

        return order;
    }

    @Override
    public List<Order> findAll() {
        return orders.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        orders.remove(id);
    }

    @Override
    public Optional<Order> findOrderById(Long id) {
        return Optional.ofNullable(orders.get(id));
    }
}
