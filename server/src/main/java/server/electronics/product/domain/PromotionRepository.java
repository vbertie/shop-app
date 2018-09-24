package server.electronics.product.domain;


import org.springframework.data.repository.Repository;
import java.util.Optional;
import java.util.Set;

@org.springframework.stereotype.Repository
interface PromotionRepository extends Repository<Promotion, Long> {

    void save(Promotion promotion);

    Optional<Promotion> findById(Long id);

    void deleteById(Long id);

    Set<Promotion> findAll();
}
