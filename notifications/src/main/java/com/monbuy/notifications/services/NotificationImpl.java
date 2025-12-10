package com.monbuy.notifications.services;

import java.time.LocalDateTime;
import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.monbuy.notifications.entity.Notification;
import com.monbuy.notifications.repository.NotificationRepo;
@Service
public class NotificationImpl implements NotificationServices {
    
    @Autowired
    private NotificationRepo notificationRepo;
    @Override
    public List<Notification> findNoticationsById(Long Id) {
        return (List<Notification>) notificationRepo.findByUserId(Id);
    }
   
    @Override
    public void sendNotification(Notification notification) {
        notification.setTimestamp(LocalDateTime.now());
        notificationRepo.save(notification);
        
        
    }
   
    
    
}
