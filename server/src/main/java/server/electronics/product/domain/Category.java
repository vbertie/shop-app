package server.electronics.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import server.electronics.product.domain.dto.category.ShowCategoryDto;
import server.electronics.product.domain.dto.product.ProductDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products;

    @CreatedDate
    @Column(insertable = true, updatable = false)
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Column(insertable = true, updatable = true)
    private LocalDateTime lastModifiedDate;

    public Category(String name){
        this.name = name;
    }

    public ShowCategoryDto dto(){
        return ShowCategoryDto.builder()
                .id(id)
                .name(name)
                .products(filterProducts(products))
                .build();
    }
    private Set<ProductDto> filterProducts(Set<Product> products){
        if (products == null) {
            return null;
        }

        return products.stream()
                .map(Product::dto)
                .collect(Collectors.toSet());
    }
}