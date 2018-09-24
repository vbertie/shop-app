package server.electronics.product.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import server.electronics.product.domain.dto.product.ProductDto;
import server.electronics.util.auditor.CustomAuditorAware;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@EntityListeners(CustomAuditorAware.class)
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

    public Product(Long id, String name, @Digits(fraction = 2, integer = 7) BigDecimal price,
                   @Min(0) int inStockNumber, String description, Boolean active, Category category) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.inStockNumber = inStockNumber;
        this.description = description;
        this.active = active;
    }

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
