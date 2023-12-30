package com.challenge.courierserv.service.impl;

import com.challenge.courierserv.models.DeliveryEntry;
import com.challenge.courierserv.models.events.DeliveryEvent;
import com.challenge.courierserv.models.events.EventTypeEnum;
import com.challenge.courierserv.repository.DeliveryEntryRepository;
import com.challenge.courierserv.service.DeliverySyncService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * This service keep the Read-only database updated based on delivery events (create, bonus or adjustment)
 */
@AllArgsConstructor
@Service
@Log4j2
public class DeliverySyncServiceImpl implements DeliverySyncService {

    private final DeliveryEntryRepository deliveryRepository;

    @Transactional
    @Override
    public void syncCreateDelivery(DeliveryEvent event) {

        //TODO Create enum for state representation - 1 Create, 2 Adjustment, 3 Bonus

        if (EventTypeEnum.CREATE_DELIVERY.equals(event.getEventType())) {
            DeliveryEntry newEntry = DeliveryEntry.builder()
                    .state(1)
                    .createdTimestamp(ObjectUtils.defaultIfNull(event.getEventTimestamp(), LocalDateTime.now()))
                    .value(event.getValue())
                    .courierId(event.getCourierId())
                    .deliveryId(event.getDeliveryId())
                    .build();
            deliveryRepository.save(newEntry);
            log.info("Delivery created successfully. Updated Delivery:" + event.getDeliveryId());
        }
    }

    @Transactional
    @Override
    public void syncUpdateDelivery(DeliveryEvent event) {

        if(EventTypeEnum.ADJUST_DELIVERY.equals(event.getEventType()) ||
                EventTypeEnum.ADD_BONUS_DELIVERY.equals(event.getEventType())){
            final Optional<DeliveryEntry> originalDeliveryEntry = deliveryRepository.findById(event.getDeliveryId());
            if(originalDeliveryEntry.isPresent()){
                deliveryRepository.save(updateDeliveryWithEvent(originalDeliveryEntry.get(),event));
                log.info("Delivery updated successfully. Updated Delivery:" + event.getDeliveryId());
            }else{
                //TODO create custom exception
                //Rollback the transaction and will be able to consume message later.
                // It may happen when receive the update event before the create
                throw new RuntimeException("It is not possible to update the delivery.");
            }
        }
    }

    private DeliveryEntry updateDeliveryWithEvent(DeliveryEntry delivery, DeliveryEvent event){
        if(EventTypeEnum.ADJUST_DELIVERY.equals(event.getEventType())){
            delivery.setState(2);
        }else{
            delivery.setState(3);
        }
        delivery.setLastChangedTimestamp(event.getEventTimestamp());
        delivery.setValue(delivery.getValue().add(event.getValue()));
        return delivery;
    }

}
