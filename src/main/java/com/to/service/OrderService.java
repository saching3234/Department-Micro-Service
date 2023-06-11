package com.to.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.to.dto.OrderResponse;
import com.to.entity.Order;
import com.to.util.Notification;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

    OrderResponse checkOutOrder(Order order) {
    	//call the send email notification class's static method
    	String message = Notification.sendEmail(order.getEmailId());
    	return new OrderResponse(order,message,HttpStatus.OK.value());
    }
}
