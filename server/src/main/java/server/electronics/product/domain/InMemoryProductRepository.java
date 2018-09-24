package server.electronics.product.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
class InMemoryProductRepository implements ProductRepository{

    private ConcurrentHashMap<Long, Product> products = new ConcurrentHashMap<>();

    @Override
    public Product save(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return  products.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        products.remove(id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        requireNonNull(id);
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findProductByCategoryName(String category) {
        return products.values()
                .stream()
                .filter(product -> product.getCategory().getName().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByKeyword(String keyword) {
        return products.values()
                .stream()
                .filter(product -> containsKeyword(product, keyword))
                .collect(Collectors.toList());
    }

    @Override
    public Set<Product> findAllByPromotionId(Long id) {
        return products.values()
                .stream()
                .filter(product -> product.getPromotion() != null)
                .filter(product -> product.getPromotion().getId() == id)
                .collect(Collectors.toSet());
    }

    @Override
    public List<Product> findAllByPromotionNotNull() {
        return products.values()
                .stream()
                .filter(this::isAtPromotion)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByPromotionNotNullAndCategoryName(String category) {
        return products.values()
                .stream()
                .filter(this::isAtPromotion)
                .filter(product -> product.getCategory()
                        .getName().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public void saveAll(Iterable<Product> iterable) {
        
    }

    @Override
    public long count() {
        return 0;
    }

    private boolean isAtPromotion(Product product){
        return product.getPromotion() != null ? true : false;
    }

    private static boolean containsKeyword(Product product,String keyword){
        requireNonNull(product);

        return product
                .getName()
                .toLowerCase()
                .contains(keyword.toLowerCase()) ||
                     product
                        .getDescription()
                        .toLowerCase()
                        .contains(keyword.toLowerCase());
    }
}
