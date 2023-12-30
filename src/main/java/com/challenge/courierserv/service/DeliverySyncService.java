package com.challenge.courierserv.service;


import com.challenge.courierserv.models.events.DeliveryEvent;


public interface DeliverySyncService {

    void syncUpdateDelivery(DeliveryEvent event);

    void syncCreateDelivery(DeliveryEvent event);

}
