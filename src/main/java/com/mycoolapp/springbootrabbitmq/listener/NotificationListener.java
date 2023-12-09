package com.mycoolapp.springbootrabbitmq.listener;

import com.mycoolapp.springbootrabbitmq.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {
    @RabbitListener(queues ="cool-app-queue" )
    public void handleMessage(Notification notification)
    {
        System.out.println("Messages received.");
        System.out.println(notification.toString());
    }
}
