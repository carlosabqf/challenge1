package com.challenge.courierserv.service;


import com.challenge.courierserv.models.DeliveryEntry;
import com.challenge.courierserv.vo.CourierStatementVO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface DeliveryService {

    DeliveryEntry getDelivery(String uuidDelivery);

    List<DeliveryEntry> getAllDeliveriesFromCourier(String courierId);

    List<DeliveryEntry> getDeliveriesFromCourierByDate(String courierID, Date startDateTime, Date endDateTime);

    CourierStatementVO getCourierStatementByDate(String courierID, Date startDateTime, Date endDateTime);

}
