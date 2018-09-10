package server.electronics.order.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String town;

    @Enumerated(EnumType.STRING)
    @Column(length = 25, nullable = false)
    private Province province;

    @CreatedDate
    @Column(insertable = true, updatable = false)
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Column(insertable = true, updatable = true)
    private LocalDateTime lastModifiedDate;
}
