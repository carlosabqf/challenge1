package com.challenge.courierserv.service;


import com.challenge.courierserv.models.DeliveryEntry;
import com.challenge.courierserv.vo.CourierStatementVO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public interface DeliveryService {

    public DeliveryEntry getDelivery(String uuidDelivery);

    public List<DeliveryEntry> getAllDeliveriesFromCourier(String courierId);

    public List<DeliveryEntry> getDeliveriesFromCourierByDate(String courierID, Date startDateTime, Date endDateTime);

    public CourierStatementVO getCourierStatementByDate(String courierID, Date startDateTime, Date endDateTime);

}
