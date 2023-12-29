package com.challenge.courierserv.controller;

import com.challenge.courierserv.models.DeliveryEntry;
import com.challenge.courierserv.service.DeliveryService;
import com.challenge.courierserv.vo.CourierStatementVO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/{uuid}")
    public ResponseEntity<DeliveryEntry> getDeliveryEntry(@PathVariable("uuid") String uuid){
        return ResponseEntity.ok(deliveryService.getDelivery(uuid));
    }

    @GetMapping("courier/{courierid}")
    public ResponseEntity<List<DeliveryEntry>> getDeliveryEntriesByCourierAndPeriod(
            @PathVariable("courierid") String courierId,
            @RequestParam(name = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            Date startDate,
            @RequestParam(name = "endDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            Date endDate)
    {
        if(ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)){
            return ResponseEntity.ok(deliveryService.getDeliveriesFromCourierByDate(courierId, startDate, endDate));
        }else{
            return ResponseEntity.ok(deliveryService.getAllDeliveriesFromCourier(courierId));
        }

    }

    @GetMapping("courier/{courierid}/statement")
    public ResponseEntity<CourierStatementVO> getStatementByCourierAndPeriod(
            @PathVariable("courierid") String courierId,
            @RequestParam(name = "startDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            Date startDate,
            @RequestParam(name = "endDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            Date endDate)
    {

            return ResponseEntity.ok(deliveryService.getCourierStatementByDate(courierId,startDate,endDate));

    }


}
