package com.challenge.courierserv.models.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DeliveryEvent {

    /**
     * Event id (Adjustment, Bonus, Create Delivery)
     */
    String eventId;

    /**
     * Uuid of the Delivery
     */
    String deliveryId;

    /**
     * Timestamp for the event (Create delivery timestamp, Adjustment Timestamp, etc)
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime eventTimestamp;

    /**
     * Value to be applied to a Delivery
     */
    BigDecimal value;


    /**
     * Identify the type of event
     */
    EventTypeEnum eventType;

}
