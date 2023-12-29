package com.challenge.courierserv.repository;

import com.challenge.courierserv.models.DeliveryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeliveryEntryRepository extends JpaRepository<DeliveryEntry, String> {

    List<DeliveryEntry> findAllByCourierId(String courierId);

    List<DeliveryEntry> findAllByCourierIdAndCreatedTimestampBetween(String courierId, LocalDateTime createdTimestampStart, LocalDateTime createdTimestampEnd);

    List<DeliveryEntry> findAllByCreatedTimestampBetween(LocalDateTime createdTimestampStart, LocalDateTime createdTimestampEnd);

}

