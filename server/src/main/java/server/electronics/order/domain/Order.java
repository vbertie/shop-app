package server.electronics.order.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private DeliveryAddress deliveryAddress;

    @OneToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @ElementCollection
    @CollectionTable(name = "order_items")
    @Embedded
    private Set<OrderItem> orderItems;

    @Min(0)
    @Digits(integer = 7, fraction = 2)
    private BigDecimal orderPayment;

    private boolean isPaid;
    private boolean isDelivered;

    @CreatedDate
    @Column(insertable = true, updatable = false)
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Column(insertable = true, updatable = true)
    private LocalDateTime lastModifiedDate;
}
