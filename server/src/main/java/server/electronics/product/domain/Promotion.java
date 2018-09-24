package server.electronics.product.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import server.electronics.product.domain.dto.promotion.PromotionDto;
import server.electronics.util.auditor.CustomAuditorAware;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@EntityListeners(CustomAuditorAware.class)
class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "promotion")
    private Set<Product> products = new HashSet<>();

    @Min(1)
    @Digits(integer = 7, fraction = 2)
    @Column(name = "percent_reduction")
    private Integer percentageReduction;

    @Column(length = 40, name = "name")
    private String promotionName;

    @CreatedDate
    @Column(insertable = true, updatable = false)
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Column(insertable = true, updatable = true)
    private LocalDateTime lastModifiedDate;

    public PromotionDto dto(){
        return PromotionDto.builder()
                .id(id)
                .percentageReduction(percentageReduction)
                .promotionName(promotionName)
                .build();
    }
}
