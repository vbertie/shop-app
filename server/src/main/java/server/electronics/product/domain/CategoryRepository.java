package server.electronics.product.domain;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
interface CategoryRepository extends Repository<Category, Long> {

    Category save(Category category);

    Optional<Category> findById(Long id);

    void deleteById(Long id);

    List<Category> findAll();

    Optional<Category> findByName(String name);

    long count();

    void saveAll(Iterable<Category> iterable);
}
