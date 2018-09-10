package server.electronics.product.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.Set;

@org.springframework.stereotype.Repository
interface CategoryRepository extends Repository<Category, Long> {

    Category save(Category category);

    Optional<Category> findById(Long id);

    void deleteById(Long id);

    Set<Category> findAll();

    Optional<Category> findByName(String name);
}
