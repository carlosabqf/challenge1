package com.challenge.courierserv.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidMessageException extends Exception{

    private String deliveryId;
}
