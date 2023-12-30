package com.challenge.courierserv.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UnprocessableMessageException extends Exception{

    private String deliveryId;
}
