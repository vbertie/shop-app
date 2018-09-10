package server.electronics.product.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import server.electronics.product.domain.dto.product.ProductDto;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(length = 50)
    private String name;

    @Digits(fraction = 2, integer = 7)
    private BigDecimal price;

    @Digits(fraction = 2, integer = 7)
    private BigDecimal oldPrice;

    @Min(0)
    private int inStockNumber;

    @Column(length = 255)
    private String description;

    private Boolean active;

    @CreatedDate
    @Column(insertable = true, updatable = false)
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Column(insertable = true, updatable = true)
    private LocalDateTime lastModifiedDate;

    public ProductDto dto(){
        return ProductDto.builder()
                .id(id)
                .name(name)
                .price(price)
                .description(description)
                .inStockNumber(inStockNumber)
                .active(active)
                .type(category.getName())
                .oldPrice(oldPrice)
                .build();
    }


}
