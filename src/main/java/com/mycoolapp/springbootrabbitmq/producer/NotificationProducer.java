package com.mycoolapp.springbootrabbitmq.producer;

import com.mycoolapp.springbootrabbitmq.model.Notification;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class NotificationProducer {
    @Value("${sr.rabbit.routing.name}")
    private String routingName;
    @Value("${sr.rabbit.exchange.name}")
    private String exchangeName;
    @PostConstruct
    public void init()
    {
        Notification notification=new Notification();
        notification.setNotificationId(UUID.randomUUID().toString());
        notification.setCreatedAt(new Date());
        notification.setMessage("Cool App message sended.");
        notification.setSeen(Boolean.FALSE);
        sentToQueue(notification);
    }
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sentToQueue(Notification notification)
    {
        System.out.println("Notification ID :"+notification.getNotificationId());
        rabbitTemplate.convertAndSend(exchangeName,routingName,notification);
    }
}
