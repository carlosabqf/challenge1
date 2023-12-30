package com.challenge.courierserv.consumer;

import com.challenge.courierserv.models.events.DeliveryEvent;
import com.challenge.courierserv.models.events.EventTypeEnum;
import com.challenge.courierserv.service.DeliverySyncService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@AllArgsConstructor
public class DeliveryEventConsumer {

    private final DeliverySyncService syncService;

    @Bean
    public Jackson2JsonMessageConverter consumerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @RabbitListener(queues = "${delivery.queue.name}")
    public void consumeDeliveryEventFromQueue(DeliveryEvent event){
        log.info("Event received successfully. Ready to consume: " + event.getEventType() );
        if (EventTypeEnum.CREATE_DELIVERY.equals(event.getEventType())){
            syncService.syncCreateDelivery(event);
        }else{
            syncService.syncUpdateDelivery(event);
        }
    }

}
