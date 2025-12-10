package com.monbuy.notifications.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.monbuy.notifications.entity.Notification;
@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> , CrudRepository<Notification, Long> {
   List<Notification> findByUserId(Long Id);
    
}
