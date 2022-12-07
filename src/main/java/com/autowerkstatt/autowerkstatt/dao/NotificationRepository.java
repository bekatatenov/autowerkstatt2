package com.autowerkstatt.autowerkstatt.dao;

import com.autowerkstatt.autowerkstatt.entity.Car;
import com.autowerkstatt.autowerkstatt.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "select * from notification " +
            "where status != 'NEW'", nativeQuery = true)
    List<Notification> getNotificationByStatus();

    @Query(value = "select * from notification n where n.user_id = :user_id", nativeQuery = true)
    List<Notification> findNotificationByUser(@Param("user_id") Long userId);
}
