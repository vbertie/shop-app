package server.electronics.order.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
class DeliveryAddress {

    @Embedded
    private City city;

    @Column(length = 40, nullable = false)
    private String street;

    @Column(length = 6, nullable = false)
    private String zipCode;

    @CreatedDate
    @Column(insertable = false, updatable = false)
    @Setter(value = AccessLevel.NONE)
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Column(insertable = false, updatable = false)
    @Setter(value = AccessLevel.NONE)
    private LocalDateTime lastModifiedDate;
}
