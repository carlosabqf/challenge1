package com.challenge.courierserv.models.events;

import lombok.Data;

@Data
public class DeliveryEvent {

    String message;
    Integer eventType;
    Double value;

}
