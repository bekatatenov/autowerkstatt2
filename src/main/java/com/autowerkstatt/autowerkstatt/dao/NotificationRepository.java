package com.autowerkstatt.autowerkstatt.dao;

import com.autowerkstatt.autowerkstatt.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "select c.id, u.first_name, u.last_name, mk.name, m.name " +
            "from cars c " +
            "inner join models m on m.id = c.models_id " +
            "inner join marks mk on mk.id = m.mark_id " +
            "inner join users u on u.id = c.user_id " +
            "where m.id = :id", nativeQuery = true)
    List<Object[]> getByCar(Long id);
}
