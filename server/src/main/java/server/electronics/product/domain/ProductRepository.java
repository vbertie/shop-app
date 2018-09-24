package server.electronics.product.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

interface ProductRepository extends Repository<Product, Long>{

    Product save(Product product);

    List<Product> findAll();

    void deleteById(Long id);

    @Query(value = "SELECT * FROM product WHERE id= ?1", nativeQuery = true)
    Optional<Product> findById(Long id);

    List<Product> findProductByCategoryName(String category);

    @Query(value = "SELECT * FROM product WHERE name LIKE %?1% OR description LIKE %?1% OR category_id IN(SELECT id FROM category WHERE name like %?1%)", nativeQuery = true)
    List<Product> findByKeyword(String keyword);

    Set<Product> findAllByPromotionId(Long id);

    List<Product> findAllByPromotionNotNull();

    List<Product> findAllByPromotionNotNullAndCategoryName(String category);

    void saveAll(Iterable<Product> iterable);

    long count();
}
