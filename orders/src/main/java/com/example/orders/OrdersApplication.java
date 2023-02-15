package com.example.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
public class OrdersApplication {

    @Autowired
    private NotificationServiceFeignClient feignClient;

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class,args);
    }

    @GetMapping("/doOrder")
    public String doOrder(@RequestParam(name = "order") String orderName) {
        try {
            feignClient.sendNotification("You make payments " + orderName);
            return "Order " + orderName + " already been created!";
        } catch (Exception e) {
            return "Notification "+ orderName +" not send";
        }
    }
}
