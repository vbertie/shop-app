package server.electronics.product.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class InMemoryCategoryRepository implements CategoryRepository {

    private Map<Long,Category> categories = new ConcurrentHashMap<>();
    private Long nextId = 1L;

    @Override
    public Category save(Category category) {
        Long id = category.getId() == null ? nextId++ : category.getId();
        return categories.put(id, category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(categories.get(id));
    }

    @Override
    public void deleteById(Long id) {
        categories.remove(id);
    }

    @Override
    public List<Category> findAll() {
        return categories.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categories.values()
                .stream()
                .filter(category -> category.getName().equalsIgnoreCase(name))
                .findAny();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void saveAll(Iterable<Category> iterable) {

    }
}
