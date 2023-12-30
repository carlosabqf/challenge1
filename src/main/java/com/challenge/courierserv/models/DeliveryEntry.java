package com.challenge.courierserv.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This Entity uses a non-normalized table for optimized queries.
 */
@Entity
@Table(name = "delivery")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryEntry {

    @Id
    @UuidGenerator
    private String deliveryId;

    @Column
    private String courierId;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdTimestamp;

    @Column
    private BigDecimal value;

    @Column
    private Integer state;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastChangedTimestamp;

}
