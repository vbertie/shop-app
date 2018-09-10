package server.electronics.product.domain;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class InMemoryPromotionRepository implements PromotionRepository{

    Map<Long,Promotion> promotions = new ConcurrentHashMap<>();

    @Override
    public void save(Promotion promotion) {
        promotions.put(promotion.getId(), promotion);
    }

    @Override
    public Optional<Promotion> findById(Long id) {
        return Optional.ofNullable(promotions.get(id));
    }

    @Override
    public void deleteById(Long id) {
        promotions.remove(id);
    }

    @Override
    public Set<Promotion> findAll() {
        return promotions.values()
                .stream()
                .collect(Collectors.toSet());
    }

}
