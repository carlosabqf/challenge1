package com.challenge.courierserv.service;


import com.challenge.courierserv.exceptions.UnprocessableMessageException;
import com.challenge.courierserv.models.events.DeliveryEvent;


public interface DeliverySyncService {

    void syncUpdateDelivery(DeliveryEvent event) throws UnprocessableMessageException;

    void syncCreateDelivery(DeliveryEvent event);

}
