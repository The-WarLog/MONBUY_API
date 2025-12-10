package com.monbuy.notifications.services;

import java.util.List;

import com.monbuy.notifications.entity.Notification;
public interface NotificationServices {
    List<Notification> findNoticationsById(Long Id);

    void sendNotification(Notification notification); //void method to send notification insteead of returning a value/Notifi

}
