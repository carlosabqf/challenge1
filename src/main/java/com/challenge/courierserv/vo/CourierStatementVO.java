package com.challenge.courierserv.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourierStatementVO {

    String courierId;
    Integer totalDeliveries;
    BigDecimal totalEarnings;

}
