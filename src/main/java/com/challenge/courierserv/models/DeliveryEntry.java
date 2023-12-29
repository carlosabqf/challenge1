package com.challenge.courierserv.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery")
@Data
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
