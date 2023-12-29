package com.challenge.courierserv.service.impl;

import com.challenge.courierserv.models.DeliveryEntry;
import com.challenge.courierserv.repository.DeliveryEntryRepository;
import com.challenge.courierserv.service.DeliveryService;
import com.challenge.courierserv.vo.CourierStatementVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
@Log4j2
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryEntryRepository deliveryRepository;

    @Override
    public DeliveryEntry getDelivery(String uuidDelivery) {
        return Optional.of(deliveryRepository.findById(uuidDelivery)).get().orElse(null);
    }

    @Override
    public List<DeliveryEntry> getAllDeliveriesFromCourier(String courierId) {

        return deliveryRepository.findAllByCourierId(courierId);

    }

    @Override
    public List<DeliveryEntry> getDeliveriesFromCourierByDate(String courierID, Date startDateTime, Date endDateTime) {

        return deliveryRepository.findAllByCourierIdAndCreatedTimestampBetween(courierID,
                startDateTime.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime(),
                endDateTime.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
    }

    @Override
    public CourierStatementVO getCourierStatementByDate(String courierID, Date startDateTime, Date endDateTime) {

        List<DeliveryEntry> allCourierDeliveriesInPeriod = deliveryRepository.findAllByCourierIdAndCreatedTimestampBetween(courierID,
                startDateTime.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime(),
                endDateTime.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());

        CourierStatementVO statementVO = new CourierStatementVO();
        statementVO.setCourierId(courierID);
        statementVO.setTotalDeliveries(allCourierDeliveriesInPeriod.size());
        statementVO.setTotalEarnings(allCourierDeliveriesInPeriod.stream()
                .map(DeliveryEntry::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add));



        return statementVO;

    }
}
