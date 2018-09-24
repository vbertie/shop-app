package server.electronics.product.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import server.electronics.product.domain.dto.category.ShowCategoryDto;
import server.electronics.product.domain.dto.product.ProductDto;
import server.electronics.util.auditor.CustomAuditorAware;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@EntityListeners(CustomAuditorAware.class)
class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products;

    @CreatedDate
    @Column(insertable = false, updatable = false)
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Column(insertable = false, updatable = false)
    private LocalDateTime lastModifiedDate;

    public Category(String name){
        this.name = name;
    }

    public Category(Long id, String name) {
        this.id = id;
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